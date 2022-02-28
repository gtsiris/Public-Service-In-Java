package public_service;

public final class Ground extends Level {
  private int priority_counter = 1;        /* Depending on when a visitor entered the building gets a unique priority number */
                                                        /* The first visitor that enters the building gets priority number 1 */
  public Ground(final int capacity, final int ground_capacity) {
    super(ground_capacity < capacity / 2 ? ground_capacity : 0);                           /* Initialize its nature as level */
    System.out.println("A Floor has been created with number0\n");
  }

  public final boolean Enter(Visitor visitor) {                     /* A visitor enters the ground, if there is enough space */
    if (visitor.Has_guest()) {                                                                     /* If visitor has a guest */
      if (this.Enough_Space_For_Two_People()) {             /* Check if there is enough space in the ground for both of them */
        this.Somebody_Enters();                                  /* Increase by 1 the current number of people in the ground */
        this.Enter(visitor.Get_guest());                                                /* Visitor's guest enters the ground */
        Get_e().Enter(visitor);                                                         /* Put them in the ground's entrance */
        return true;                                                                 /* They entered the ground successfully */
      }
    }
    else if (!this.Is_Full()) {  /* If visitor has not guest, then check if there is enough space in the ground just for him */
      this.Somebody_Enters();                                    /* Increase by 1 the current number of people in the ground */
      Get_e().Enter(visitor);                                                        /* Put visitor in the ground's entrance */
      return true;                                                                /* Visitor entered the ground successfully */
    }
    return false;                                                                       /* Visitor couldn't enter the ground */
  }
  
  public void Enter(final Guest guest) {                                                        /* A guest enters the ground */
    this.Somebody_Enters();                                      /* Increase by 1 the current number of people in the ground */
  }
  
  public void Exit(final Visitor visitor) {                                                    /* A visitor exits the ground */
    this.Somebody_Exits();                                       /* Decrease by 1 the current number of people in the ground */
    if (visitor.Has_guest())                                                                       /* If visitor has a guest */
      this.Exit(visitor.Get_guest());                                                    /* Visitor's guest exits the ground */
    Get_e().Exit(visitor);                                    /* Visitor must leave the entrance in order to exit the ground */
  }
  
  public void Exit(final Guest guest) {                                                          /* A guest exits the ground */
    this.Somebody_Exits();                                       /* Decrease by 1 the current number of people in the ground */
  }
  
  public void Wait(Visitor visitor) {                               /* A visitor waits for the lift and gets priority number */
    visitor.Set_priority(priority_counter++);                                       /* Give a unique priority to the visitor */
    System.out.println("Waiting for the lift ");
  }
}
