package public_service;

public final class Building extends Limited_Space {
  private final Lift l;                                                                                                    /* Building has a lift */
  private final Ground g;                                                                                                /* Building has a ground */
  private final Floor[] f;                                                                                            /* Building has some floors */
  
  public Building(final int capacity, final int floor_capacity, final int ground_capacity, final int office_capacity, final int lift_capacity) {
    super(capacity);                                                                                    /* Initialize its nature as limited space */
    l = new Lift(office_capacity, lift_capacity);                                                                                /* Create a lift */
    g = new Ground(capacity, ground_capacity);                                                                                 /* Create a ground */
    f = new Floor[4];                                                                             /* Allocate memory for four references to floor */
    for (int floor_number = 1; floor_number <= 4; floor_number++)
      f[floor_number - 1] = new Floor(floor_number, capacity, floor_capacity, ground_capacity, office_capacity);             /* Create each floor */
    System.out.println("A new building is ready for serving citizens!\n");
  }
    
  public final boolean Enter(Visitor visitor) {                                        /* A visitor enters the building, if there is enough space */
    if (visitor.Has_guest()) {                                                                                          /* If visitor has a guest */
      if (this.Enough_Space_For_Two_People())                                  /* Check if there is enough space in the building for both of them */
        if (g.Enter(visitor)) {                                                     /* They must access the ground in order to enter the building */
          this.Somebody_Enters();                                                   /* Increase by 1 the current number of people in the building */
          this.Enter(visitor.Get_guest());                                                                 /* Visitor's guest enters the building */
          g.Wait(visitor);                                                                                   /* Give a priority number to visitor */
          return true;                                                                                  /* They entered the building successfully */
        }
        System.out.println("Please, you and your guest come tomorrow");
        return false;                                                                                         /* They couldn't enter the building */
    }
    else if (!this.Is_Full())                       /* If visitor has not guest, then check if there is enough space in the building just for him */
      if (g.Enter(visitor)) {                                                    /* Visitor must access the ground in order to enter the building */
        this.Somebody_Enters();                                                     /* Increase by 1 the current number of people in the building */
        g.Wait(visitor);                                                                                     /* Give a priority number to visitor */
        return true;                                                                                 /* Visitor entered the building successfully */
      }
    System.out.println("Please, come tomorrow");
    return false;                                                                                          /* Visitor couldn't enter the building */
  }
  
  public final void Enter(final Guest guest) {                                                                     /* A guest enters the building */
    this.Somebody_Enters();                                                         /* Increase by 1 the current number of people in the building */
  }
  public final void Exit(final Visitor visitor) {                                                                 /* A visitor exits the building */
    this.Somebody_Exits();                                                          /* Decrease by 1 the current number of people in the building */
    System.out.println("I cannot believe I finished! " + visitor.Get_priority());
    if (visitor.Has_guest())                                                                                            /* If visitor has a guest */
      this.Exit(visitor.Get_guest());                                                                       /* Visitor's guest exits the building */
    g.Exit(visitor);                                                               /* Visitor must leave the ground in order to exit the building */
  }
  public final void Exit(final Guest guest) {                                                                       /* A guest exits the building */
    this.Somebody_Exits();                                                          /* Decrease by 1 the current number of people in the building */
    System.out.println("I cannot believe I finished! " + guest.Get_visitor().Get_priority() + "'s guest");
  }
  public final void Serve_Visitors() {                                     /* Execute a lift cycle, serve visitors and release space for new ones */  
    l.Operate(g, f);                                                                                                      /* Execute a lift cycle */
    while(g.Get_e().Get_list().More_Visitors_Ready_To_Leave())       /* Served visitors exit the building in order to realease space for new ones */
      this.Exit(g.Get_e().Get_list().Get_Visitor_Ready_To_Leave());  /* The served visitor with highest priority in the ground exits the building */
  }
}
