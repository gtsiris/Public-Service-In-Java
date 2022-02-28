package public_service;

public final class Floor extends Level {
  private final Office[] o;                                                                       /* Floor has some offices */
  private final int f_num;                                       /* The number of this floor (eg. f_num = 1 -> first floor) */
  
  public Floor(final int floor_number, final int capacity, final int floor_capacity, final int ground_capacity, final int office_capacity) {
    super(floor_capacity < capacity / 3 ? floor_capacity : 0);                            /* Initialize its nature as level */
    o = new Office[10];                                                     /* Allocate memory for ten references to office */
    for (int office_number = 1; office_number <= 10; office_number++)
      o[office_number - 1] = new Office(office_number, floor_capacity, office_capacity);              /* Create each office */
    f_num = floor_number;                                                            /* Initialize the number of this floor */
    System.out.println("A Floor has been created with number" + floor_number + "\n");
  }

  public final boolean Enter(Visitor visitor) {                     /* A visitor enters the floor, if there is enough space */
    if (visitor.Has_guest()) {                                                                    /* If visitor has a guest */
      if (this.Enough_Space_For_Two_People()) {             /* Check if there is enough space in the floor for both of them */
        this.Somebody_Enters();                                  /* Increase by 1 the current number of people in the floor */
        System.out.println("Entering floor number" + f_num + " " + visitor.Get_priority());
        this.Enter(visitor.Get_guest());                                                /* Visitor's guest enters the floor */
        if (!o[visitor.Get_dest_office() - 1].Enter(visitor))             /* Check if they can enter his destination office */
         Get_e().Enter(visitor);                                           /* If not, then put them in the floor's entrance */
        return true;                                                                 /* They entered the floor successfully */
      }
      System.out.println("Sorry, floor number" + f_num + " is full " + visitor.Get_priority());
      System.out.println("Sorry, floor number" + f_num + " is full " + visitor.Get_priority() + "'s guest");
      return false;                                                                        /* They couldn't enter the floor */
    }
    else if (!this.Is_Full()) {  /* If visitor has not guest, then check if there is enough space in the floor just for him */
      this.Somebody_Enters();                                    /* Increase by 1 the current number of people in the floor */
      System.out.println("Entering floor number" + f_num + " " + visitor.Get_priority());
      if (!o[visitor.Get_dest_office() - 1].Enter(visitor))            /* Check if visitor can enter his destination office */
        Get_e().Enter(visitor);                                             /* If not, then put him in the floor's entrance */
      return true;                                                                /* Visitor entered the floor successfully */
    }
    System.out.println("Sorry, floor number" + f_num + " is full " + visitor.Get_priority());
    return false;                                                                       /* Visitor couldn't enter the floor */
  }
  
  public final void Enter(final Guest guest) {                                                  /* A guest enters the floor */
    this.Somebody_Enters();                                      /* Increase by 1 the current number of people in the floor */
    System.out.println("Entering floor number" + f_num + " " + guest.Get_visitor().Get_priority() + "'s guest");
  }
  
  public final Visitor Exit() {                                       /* A randomly selected served visitor exits the floor */
    int office_number = Public_Service.rand.nextInt(10) + 1;                                      /* Choose a random office */
    while (o[office_number - 1].Is_Empty()) {                                   /* Repeat until you find a non-empty office */
      office_number = office_number %  10 + 1;                                       /* If it is empty, choose its next one */
    }
    Visitor visitor_ready_to_leave_the_floor = new Visitor(o[office_number - 1].Exit());
                                                       /* A randomly selected served visitor in this office exits the floor */
    this.Somebody_Exits();                                       /* Decrease by 1 the current number of people in the floor */
    if (visitor_ready_to_leave_the_floor.Has_guest())                                             /* If visitor has a guest */
      this.Exit(visitor_ready_to_leave_the_floor.Get_guest());                           /* Visitor's guest exits the floor */
    if (Get_e().Get_list().More_Visitors_With_This_Destination_Office(office_number)) 
                                                 /* If there are any visitors waiting in the entrance to get in this office */
      o[office_number - 1].Enter(Get_e().Get_list().Exclude_Visitor_With_This_Destination_Office(office_number));
                                     /* The one of them with the highest priority exits the entrance and enters this office */
    return visitor_ready_to_leave_the_floor;            /* Return the randomly selected served visitors that left the floor */
  }
  
  public final void Exit(final Guest guest) {                                                    /* A guest exits the floor */
    this.Somebody_Exits();                                       /* Decrease by 1 the current number of people in the floor */
  }
}
