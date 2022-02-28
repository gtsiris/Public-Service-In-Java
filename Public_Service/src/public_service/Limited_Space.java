package public_service;

public class Limited_Space extends Unlimited_Space {                        /* Limited_Space = Unlimited_Space + Limit */
  private final int N;                                                       /* Maximum number of people in this space */
  
  public Limited_Space(final int capacity) {                /* Default initialization of its nature as unlimited space */
    N = capacity;                                             /* Initialize the maximum number of people in this space */
  }
    
  public final boolean Is_Full() {                                           /* Determine if this space is full or not */
    return (this.Get_n() == N);              /* If the current number of people in this space is equal to its capacity,
                                                                                                       then it is full */
  }
  
  protected final boolean Enough_Space_For_Two_People() {  /* Determine if there is enough space for two people or not */
    return (this.Get_n() <= N - 2);
                                  /* If the current number of people in this space is atleast 2 less than its capacity,
                                                                        then there is enough space for two more people */
  }
}
