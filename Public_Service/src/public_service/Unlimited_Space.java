package public_service;

public class Unlimited_Space {
  private int n = 0;                  /* Current number of people in this space */
    
  public final boolean Is_Empty() {  /* Determine if this space is empty or not */
    return (n == 0);       /* If there is noone in this space, then it is empty */
  }
    
  public final int Get_n() {  /* Get the current number of people in this space */
    return n;              /* Return the current number of people in this space */
  }
    
  protected final void Somebody_Enters() {        /* A person enters this space */
    n++;            /* Increase by 1 the current number of people in this space */
  }
    
  protected final void Somebody_Exits() {          /* A person exits this space */
    n--;            /* Decrease by 1 the current number of people in this space */
  }
}
