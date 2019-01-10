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

        if not startingWorld:
            self.InitialWorld = []
            self.InitialWorld[0] = random.randint(0, 2)
            self.InitialWorld[1] = random.randint(0, 2)
        else:
            self.InitialWorld = copy.deepcopy(startingWorld)

    def run(self, agent, start = None):
        self.initializeRun(agent, start)

        while self.time < 1000:
            agentAction = agent.program(self.perceive())

            assert agentAction in self.possibleActions, "Invalid action: {0}".format(agentAction)
            if agentAction == "Right":
                self.agentPosition = 1
            elif agentAction == "Left":
                self.agentPosition = 0
            elif agentAction == "Suck":
                self.world[self.agentPosition] = 1

            self.agentScore += sum(self.world)

            self.time += 1

        return "Agent Score: {0} points".format(self.agentScore)

    def initializeRun(self, agent, start):
        self.time = 0
        self.world = copy.deepcopy(self.InitialWorld)
        self.agentScore = 0

        if not start:
            # 0 means tile A; 1 means tile B
            self.agentPosition = random.randint(0, 2)
        else:
            assert (start == "A") or (start == "B"), "Starting tile must be \'A\' of \'B\'"
            if start == "A":
                self.agentPosition = 0
            else:
                self.agentPosition = 1

        agent.initializeState()

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

class VacuumWorldMovementPentalty(VacuumWorld):
    def run(self, agent, start = None):
        self.initializeRun(agent, start)

        while self.time < 1000:
            agentAction = agent.program(self.perceive())

            assert agentAction in self.possibleActions, "Invalid action: {0}".format(agentAction)
            if agentAction == "Right":
                if self.agentPosition == 0:
                    self.agentScore -= 1

                self.agentPosition = 1
            elif agentAction == "Left":
                if self.agentPosition == 1:
                    self.agentScore -= 1

                self.agentPosition = 0
            elif agentAction == "Suck":
                self.world[self.agentPosition] = 1

            self.agentScore += sum(self.world)

            self.time += 1

        return "Agent Score: {0} points".format(self.agentScore)

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

environmentPenalty00.run(vacuum, A)
