package public_service;

public final class Lift extends Limited_Space {
  private final Visitor_List list;                                                      /* Includes the visitors that are currently in the lift */
  private int current_f = 0;                                                                 /* The floor that the lift is currently stopped at */
                                                                                               /* The lift is initially stopped at ground floor */
  public Lift(final int office_capacity, final int lift_capacity) {
    super(lift_capacity > office_capacity ? lift_capacity : 0);
    list = new Visitor_List();                                                                                         /* Create a visitor list */
    System.out.println("A lift has been created\n");
  }

  public final void Operate(Ground g, Floor f[]) {                  /* Lift executes a cycle. During a cycle some visitors embark and disembark */
    int visitors_in_the_ground = g.Get_e().Get_list().Size();
                                                            /* Initialize the number of visitors in the ground as the size of the ground's list */
    for (int visitor_counter = 1, visitor_position = 1; visitor_counter <= visitors_in_the_ground; visitor_counter++) {
        if(this.Enter(g.Get_e().Get_list().Get_ith_Visitor(visitor_position)))             /* Each visitor in ground attempts to enter the lift */
          g.Exit(g.Get_e().Get_list().Get_ith_Visitor(visitor_position));                         /* If he can enter, then he leaves the ground */
                        /* In this case don't increase the position, because the next visitor will appear at the same position of ground's list */
        else
          visitor_position++;                                /* If he can't enter, then increase the position in order to find the next visitor */
    }
    this.Stop_Up(f);                                                     /* Lift goes upwards and stops at each floor so visitors can disembark */
    this.Stop_Down(f);                                                    /* Lift goes downwards and stops at each floor so visitors can embark */
    this.Empty_All(g);                                                            /* All the served visitors exit the lift and enter the ground */
  }

  public final boolean Enter(Visitor visitor) {                                          /* A visitor enters the lift, if there is enough space */
    if (visitor.Has_guest()) {                                                                                        /* If visitor has a guest */
      if (this.Enough_Space_For_Two_People()) {                                  /* Check if there is enough space in the lift for both of them */
        this.Somebody_Enters();                                                       /* Increase by 1 the current number of people in the lift */
        System.out.println("Visitor in the lift " + visitor.Get_priority());
        list.Add_Visitor(visitor);                                                                                   /* Add them to lift's list */
        this.Enter(visitor.Get_guest());                                                                     /* Visitor's guest enters the lift */
        return true;                                                                                      /* They entered the lift successfully */
      }
      System.out.println("You are not allowed to enter! " + visitor.Get_priority());
      System.out.println("You are not allowed to enter! " + visitor.Get_priority() + "'s guest");
      return false;                                                                                             /* They couldn't enter the lift */
    }
    else if (!this.Is_Full()) {                       /* If visitor has not guest, then check if there is enough space in the lift just for him */
      this.Somebody_Enters();                                                         /* Increase by 1 the current number of people in the lift */
      System.out.println("Visitor in the lift " + visitor.Get_priority());
      list.Add_Visitor(visitor);                                                                                  /* Add visitor to lift's list */
      return true;                                                                                     /* Visitor entered the lift successfully */
    }
    System.out.println("You are not allowed to enter! " + visitor.Get_priority());
    return false;                                                                                            /* Visitor couldn't enter the lift */
  }

  public final void Enter(final Guest guest) {                                                                       /* A guest enters the lift */
    this.Somebody_Enters();                                                           /* Increase by 1 the current number of people in the lift */
    System.out.println("Guest in the lift " + guest.Get_visitor().Get_priority() + "'s guest");
  }

  public final void Stop_Up(Floor f[]) {                                 /* Lift goes upwards and stops at each floor so visitors can disembark */
    while (++current_f != 0) {                                                                     /* Lift goes upwards and stops at each floor */
      int visitors_in_the_lift = this.list.Size();
      for (int visitor_counter = 1, visitor_position = 1; visitor_counter <= visitors_in_the_lift; visitor_counter++) {
        if (list.Get_ith_Visitor(visitor_position).Get_dest_floor() == current_f) {     /* Each visitor in the lift with this destination floor */
          if(f[current_f - 1].Enter(list.Get_ith_Visitor(visitor_position)))                                     /* attempts to enter the floor */
            this.Exit(list.Get_ith_Visitor(visitor_position));                                      /* If he can enter, then he leaves the lift */
                          /* In this case don't increase the position, because the next visitor will appear at the same position of lift's list */
          else
            visitor_position++;                              /* If he can't enter, then increase the position in order to find the next visitor */
        }
        else
          visitor_position++;             /* If a visitor has other destination floor, then increase the position in order to find the next one */
      }
      if (current_f == 4)                                                                                  /* If lift reached the highest floor */
        break;                                                                                                   /* then it stops going upwards */
    }
  }

  public final void Exit(final Visitor visitor_ready_to_leave_the_lift) {                                           /* A visitor exits the lift */
    this.Somebody_Exits();                                                            /* Decrease by 1 the current number of people in the lift */
    if (visitor_ready_to_leave_the_lift.Has_guest())                                                                  /* If visitor has a guest */
      this.Exit(visitor_ready_to_leave_the_lift.Get_guest());                                                 /* Visitor's guest exits the lift */
    list.Exclude_This_Visitor(visitor_ready_to_leave_the_lift);                                       /* Erase the visitor from the lift's list */
  }

  public final void Exit(final Guest guest) {                                                                         /* A guest exits the lift */
    this.Somebody_Exits();                                                            /* Decrease by 1 the current number of people in the lift */
  }

  public final void Stop_Down(Floor f[]) {                                /* Lift goes downwards and stops at each floor so visitors can embark */
    while(current_f != 0) {                                                                        /* Lift stops at each floor until the ground */
     while(!this.Is_Full() && !f[current_f - 1].Is_Empty())       /* If there is enough space in the lift and atleast one visitor in this floor */
       this.Enter(f[current_f - 1].Exit());                                                    /* A visitor exits the floor and enters the lift */
     current_f--;                                                                                                       /* Lift going downwards */
   }
  }

  public final void Empty_All(Ground g) {                                         /* All the served visitors exit the lift and enter the ground */
    while (list.More_Visitors_Ready_To_Leave()) {                              /* Repeat until all served visitors in the lift enter the ground */
      if (g.Enter(list.Get_Visitor_Ready_To_Leave()))  /* The served visitor in the lift with the highest priority attempts to enter the ground */
        this.Exit(list.Get_Visitor_Ready_To_Leave());                                               /* If he can enter, then he leaves the lift */
      else
        break;                                               /* If there is not enough space in the ground, the rest of them remain in the lift */
    }
  }
}
