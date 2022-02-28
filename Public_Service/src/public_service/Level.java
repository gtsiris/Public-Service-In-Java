package public_service;

public class Level extends Limited_Space {
  private final Entrance e;                  /* Level has an entrance */
  
  public Level(final int level_capacity) {
    super(level_capacity);  /* Initialize its nature as limited space */
    e = new Entrance();
  }
    
  public final Entrance Get_e() {   /* Get the entrance of this level */
    return e;                    /* Return the entrance of this level */
  }
}
