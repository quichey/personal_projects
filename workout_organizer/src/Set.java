/**
 * Created by Quichey on 5/13/17.
 */
public class Set
{
    private AttributeRecord weight;
    private AttributeRecord reps;
    private AttributeRecord time;
    private AttributeRecord intensity;

    public Set(Attribute weight, Attribute reps, Attribute time, Attribute intensity)
    {
        this.weight = new AttributeRecord(weight);
        this.reps = new AttributeRecord(reps);
        this.time = new AttributeRecord(time);
        this.intensity = new AttributeRecord(intensity);
    }

    public AttributeRecord getAttributeRecord(int index)
    {
        if (index == 0)
            return weight;
        else if (index == 1)
            return reps;
        else if (index == 2)
            return time;
        else
            return intensity;
    }
}
