package public_service;

public final class Office extends Limited_Space {
  private final Visitor_List list;                                /* Includes the visitors that are currently in this office */
  private final int o_num;                                      /* The number of this office (eg. o_num = 1 -> first office) */
  
  public Office(final int office_number, final int floor_capacity, final int office_capacity) {
    super(office_capacity < floor_capacity ? office_capacity : 0);                 /* Initialize its nature as limited space */
    list = new Visitor_List();                                                                      /* Create a visitor list */
    o_num = office_number;                                                           /* Initialize the number of this office */
    System.out.println("Office number" + o_num + " has been created");
  }

  public final boolean Enter(Visitor visitor) {                   /* A visitor enters the building, if there is enough space */
    if (visitor.Has_guest()) {                                                                     /* If visitor has a guest */
      if (this.Enough_Space_For_Two_People()) {             /* Check if there is enough space in the office for both of them */
        this.Somebody_Enters();                                  /* Increase by 1 the current number of people in the office */
        System.out.println("Entering office number" + o_num + " " + visitor.Get_priority());
        this.Enter(visitor.Get_guest());                                                /* Visitor's guest enters the office */
        list.Add_Visitor(visitor);                                                              /* Add them to office's list */
        return true;                                                                 /* They entered the office successfully */
      }
      System.out.println("Please, wait outside for entrance in office number" + o_num + " " + visitor.Get_priority());
      System.out.println("Please, wait outside for entrance in office number" + o_num + " " + visitor.Get_priority() + "'s guest");
      return false;
    }
    else if (!this.Is_Full()) {  /* If visitor has not guest, then check if there is enough space in the office just for him */
      this.Somebody_Enters();                                    /* Increase by 1 the current number of people in the office */
      System.out.println("Entering office number" + o_num + " " + visitor.Get_priority());
      list.Add_Visitor(visitor);                                                             /* Add visitor to office's list */
      return true;                                                                /* Visitor entered the office successfully */
    }
    System.out.println("Please, wait outside for entrance in office number" + o_num + " " + visitor.Get_priority());
    return false;
  }

  public final void Enter(final Guest guest) {                                                  /* A guest enters the office */
    this.Somebody_Enters();                                      /* Increase by 1 the current number of people in the office */
    System.out.println("Entering office number" + o_num + " " + guest.Get_visitor().Get_priority() + "'s guest");
  }

  public final Visitor Exit() {                                       /* A randomly selected served visitor exits the office */
    Visitor visitor_ready_to_leave = new Visitor(list.Exclude_ith_Visitor(Public_Service.rand.nextInt(this.list.Size()) + 1));
                                                                 /* Erase a randomly selected visitor from the office's list */
   visitor_ready_to_leave.Set_ready_to_leave();                                                        /* Mark him as served */
   this.Somebody_Exits();                                        /* Decrease by 1 the current number of people in the office */
   if (visitor_ready_to_leave.Has_guest())                                                         /* If visitor has a guest */
     this.Exit(visitor_ready_to_leave.Get_guest());                                      /* Visitor's guest exits the office */
   return visitor_ready_to_leave;                                                               /* Return the served visitor */
  }

  public final void Exit(final Guest guest) {                                                    /* A guest exits the office */
    this.Somebody_Exits();                                       /* Decrease by 1 the current number of people in the office */
  }
}
