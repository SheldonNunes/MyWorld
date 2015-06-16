package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TerrainGeneration {
	TerrainType Terrain;
    int posX;
    int posY;
	private List<TerrainGeneration> myGrid;
	
    public TerrainGeneration(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
	
    public Color tellItLikeItIs(TerrainType Terrain) {
        switch (Terrain) { 	
            case SEA:
            	return Color.BLUE;

            case LAKE:
                return Color.CYAN;

            case BEACH:
            	return Color.YELLOW;

            
            case MOUNTAIN:
                return Color.GRAY;
                        
            case LAND:
                return Color.GREEN;
                
            case DESERT:
            	return Color.ORANGE;
        }
		return null;
    }
    
    public void Generate() {
    }
    
	List<TerrainGeneration> getGrid() {
		return myGrid;
	}


}