package common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import enums.Science;
import enums.Terrain;

public class Coord {
	public int xpos;
	Terrain terrain;
    boolean hasRover;
    Science science;
    
	@Override
	public String toString() {
	    return terrain + " " + science + " " + xpos + " " + ypos;
	}

	public int ypos;
	
	public Coord(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
    public Coord(Terrain terrain, Science science, int x, int y) {
        this(x, y);
        this.science = science;
        this.terrain = terrain;
    }
	
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(xpos).
            append(ypos).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord other = (Coord) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(xpos, other.xpos).
            append(ypos, other.ypos).
            isEquals();
    }
	
}