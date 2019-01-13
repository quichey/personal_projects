import random
import copy

class VacuumWorld:
    def __init__(self, startingWorld = None):
        '''
        InitialWorld is a 2 element list
        first element is tile A
        second element is tile B
        tiles are 0 (for dirty) or 1 (for clean)
        '''
        self.possibleActions = ["Right", "Left", "Suck", None]
        self.timeSteps = 1000
        self.printStartWorld = False

        if not startingWorld:
            self.InitialWorld = []
            self.InitialWorld[0] = random.randint(0, 1)
            self.InitialWorld[1] = random.randint(0, 1)
        else:
            self.InitialWorld = copy.deepcopy(startingWorld)

    def run(self, agent, start = None):
        self.initializeRun(agent, start)

        while self.time < self.timeSteps:
            agentAction = agent.program(self.perceive())

            assert agentAction in self.possibleActions, "Invalid action: {0}".format(agentAction)
            self.processAction(agentAction)

            self.agentScore += self.numCleanSquares()

            self.time += 1

        return "Agent Score: {0} points".format(self.agentScore)

    def numCleanSquares(self):
        return sum(self.world)

    def processAction(self, agentAction):
        self.performAction(agentAction)

    def performAction(self, agentAction):
        if agentAction == "Right":
            self.agentPosition = 1
        elif agentAction == "Left":
            self.agentPosition = 0
        elif agentAction == "Suck":
            self.world[self.agentPosition] = 1

    def initializeRun(self, agent, start):
        self.time = 0
        self.world = copy.deepcopy(self.InitialWorld)
        self.agentScore = 0

        self.initializePosition(start)

        agent.initializeState()

        if self.printStartWorld:
            print("\nInitial World:\n")
            self.printInitialWorld()
            print("\nInitial World with agent:\n")
            self.printWorld()

    def initializePosition(self, start):
        if not start:
            # 0 means tile A; 1 means tile B
            self.setValidRandomPosition()
        else:
            self.validateStartPosition(start)
            self.setToInputStart(start)

    def setValidRandomPosition(self):
        self.agentPosition = random.randint(0, 1)

    def validateStartPosition(self, start):
        assert (start == "A") or (start == "B"), "Starting tile must be \'A\' of \'B\'"

    def setToInputStart(self, start):
        if start == "A":
            self.agentPosition = 0
        else:
            self.agentPosition = 1

    def perceive(self):
        percept = []

        if self.agentPosition:
            percept.append("B")
            if self.world[1]:
                percept.append("Clean")
            else:
                percept.append("Dirty")
        else:
            percept.append("A")
            if self.world[0]:
                percept.append("Clean")
            else:
                percept.append("Dirty")

        return percept

    def printInitialWorld(self):
        return

    def printWorld(self):
        return

class VacuumWorldMovementPentalty(VacuumWorld):
    def processAction(self, agentAction):
        if self.isValidMove(agentAction):
            self.agentScore -= 1
        self.performAction(agentAction)

    def isValidMove(self, action):
        if action == "Right":
            if self.agentPosition == 0:
                return True
        elif action == "Left":
            if self.agentPosition == 1:
                return True
        else:
            return False

class VacuumWorld2D(VacuumWorldMovementPentalty):
    def __init__(self, worldLength, worldWidth, startingWorld = None):
        self.worldLength = worldLength
        self.worldWidth = worldWidth

        self.possibleActions = ["Right", "Left", "Up", "Down", "Suck", None]
        self.timeSteps = 1000
        self.printStartWorld = False
        self.printWorldsEachRound = False

        if not startingWorld:
            self.InitialWorld = []
            for _ in range(worldLength):
                row = []
                for _ in range(worldWidth):
                    #0 means dirty, 1 means clean, 2 means obstacle
                    row.append(random.randint(0, 2))
                self.InitialWorld.append(row)
        else:
            self.InitialWorld = copy.deepcopy(startingWorld)

    def setValidRandomPosition(self):
        self.setRandomPosition()
        while self.isObstacle(self.agentPosLen, self.agentPosWid):
            self.setRandomPosition()

    def setRandomPosition(self):
        self.agentPosLen = random.randint(0, self.worldLength - 1)
        self.agentPosWid = random.randint(0, self.worldWidth - 1)

    def validateStartPosition(self, start):
        assert isinstance(start, list) and (len(start) == 2), "Invalid format for starting tile"
        assert (start[0] in range(0, self.worldLength)) and (start[1] in range(0, self.worldWidth)), "Starting tile not in range"
        assert not self.isObstacle(start[0], start[1]), "Starting tile is obstacle"

    def setToInputStart(self, start):
        self.agentPosLen = start[0]
        self.agentPosWid = start[1]

    def isObstacle(self, l, w):
        return self.InitialWorld[l][w] == 2

    def isValidMove(self, action):
        if action == "Suck" or action == None:
            return False
        elif action == "Right":
            return self.canMoveRight()
        elif action == "Left":
            return self.canMoveLeft()
        elif action == "Up":
            return self.canMoveUp()
        elif action == "Down":
            return self.canMoveDown()

    def canMoveRight(self):
        return self.agentPosWid < self.worldWidth - 1 and not self.isObstacle(self.agentPosLen, self.agentPosWid + 1)

    def canMoveLeft(self):
        return self.agentPosWid > 0 and not self.isObstacle(self.agentPosLen, self.agentPosWid - 1)

    def canMoveUp(self):
        return self.agentPosLen > 0 and not self.isObstacle(self.agentPosLen - 1, self.agentPosWid)

    def canMoveDown(self):
        return self.agentPosLen < self.worldLength - 1 and not self.isObstacle(self.agentPosLen + 1, self.agentPosWid)

    def performAction(self, agentAction):
        if agentAction == "Suck":
            self.world[self.agentPosLen][self.agentPosWid] = 1
        elif agentAction == "Right" and self.canMoveRight():
            self.agentPosWid += 1
        elif agentAction == "Left" and self.canMoveLeft():
            self.agentPosWid -= 1
        elif agentAction == "Up" and self.canMoveUp():
            self.agentPosLen -= 1
        elif agentAction == "Down" and self.canMoveDown():
            self.agentPosLen += 1
        if self.printWorldsEachRound:
            print("\nAgent Action: {0}\n".format(agentAction))
            self.printWorld()
            print("\n")

    def printInitialWorld(self):
        for row in self.world:
            print(row)

    def printWorld(self):
        worldWithAgent = copy.deepcopy(self.world)
        worldWithAgent[self.agentPosLen][self.agentPosWid] = 3
        for row in worldWithAgent:
            print(row)

    def perceive(self):
        return [(self.agentPosLen, self.agentPosWid), self.world[self.agentPosLen][self.agentPosWid]]

    def numCleanSquares(self):
        total = 0
        for row in self.world:
            for tile in row:
                if tile == 1:
                    total += 1
        return total

class VacuumAgentReflex:
    def initializeState(self):
        return

    def program(self, percept):
        if percept[1] == "Dirty":
            return "Suck"
        elif percept[0] == "A":
            return "Right"
        elif percept[0] == "B":
            return "Left"

class VacuumAgentModelBased(VacuumAgentReflex):
    def initializeState(self):
        #2 means unexplored
        self.state = [2, 2]

    def program(self, percept):
        if percept[0] == "A":
            self.state[0] = 1
        elif percept[0] == "B":
            self.state[1] = 1

        if percept[1] == "Dirty":
            return "Suck"
        elif (percept[0] == "A") and (self.state[1] != 1):
            return "Right"
        elif (percept[0] == "B") and (self.state[0] != 1):
            return "Left"
        else:
            return None

class VacuumAgentReflex2D(VacuumAgentReflex):
    def initializeState(self):
        self.reactions = {}
        self.movement = {
            0: "Up",
            1: "Right",
            2: "Down",
            3: "Left"
        }

    def program(self, percept):
        assert percept[1] in [0, 1], "Percept should be 0 or 1, but is: {0}".format(percept[1])
        if percept[1] == 0:
            return "Suck"
        else:
            return self.getAction(percept)

    def getAction(self, percept):
        tile = percept[0]
        if tile not in self.reactions.keys():
            self.reactions[tile] = random.randint(0, 3)
        return self.movement[self.reactions[tile]]

class VacuumAgentReflex2D_Randomized(VacuumAgentReflex2D):
    def getAction(self, percept):
        return self.movement[random.randint(0, 3)]

vacuum = VacuumAgentReflex()
environment11 = VacuumWorld([1,1])
environment01 = VacuumWorld([0,1])
environment10 = VacuumWorld([1,0])
environment00 = VacuumWorld([0,0])
A = "A"
B = "B"

environmentPenalty11 = VacuumWorldMovementPentalty([1,1])
environmentPenalty01 = VacuumWorldMovementPentalty([0,1])
environmentPenalty10 = VacuumWorldMovementPentalty([1,0])
environmentPenalty00 = VacuumWorldMovementPentalty([0,0])

vacuum2D = VacuumAgentReflex2D()
vacuumRand = VacuumAgentReflex2D_Randomized()
environment2D = VacuumWorld2D(10, 10)
