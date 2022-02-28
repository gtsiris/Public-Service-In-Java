package public_service;

public final class Visitor extends Person {
  private int priority = Integer.MAX_VALUE;  /*  The visitor has a priority number that shows his placing in every queue */
                                                                /* The visitor initially has the worst possible priority */
  private boolean ready_to_leave = false;                             /* Flag that shows if the visitor is served or not */
    /* initially he is not served */
  private final Guest guest;                                                    /* A guest might accompanies the visitor */
  
  public Visitor() {
    super(Public_Service.rand.nextInt(4) + 1, Public_Service.rand.nextInt(10) + 1);   /* Initialize his nature as person */
                                              /* Generate a random number between 1 and 4 as visitor's destination floor */
                                            /* Generate a random number between 1 and 10 as visitor's destination office */
    if (Public_Service.rand.nextInt(2) == 1)                        /* Randomly choose if the visitor has a guest or not */
      guest = new Guest(this);                                                                         /* Create a guest */
    else
      guest = null;
  }
  
  public Visitor(Visitor visitor) {                                                                  /* Copy constructor */
    super(visitor.Get_dest_floor(), visitor.Get_dest_office());                       /* Initialize his nature as person */
    priority = visitor.Get_priority();                                                                  /* Copy priority */
    ready_to_leave = visitor.Get_status();                                                                /* Copy status */
    if (visitor.Has_guest())                                                           /* If the other visitor has guest */
      guest = new Guest(this);                                                                         /* Create a guest */
    else
      guest = null;
  }
  
  public final void Set_priority(final int priority_number) {     /* Set visitor's priority as the given priority number */
    priority = priority_number;                                   /* Set visitor's priority as the given priority number */
  }

  public final void Set_ready_to_leave() {                                                     /* Mark visitor as served */
    ready_to_leave = true;                                                                     /* Mark visitor as served */
  }

  public final int Get_priority() {                                                       /* Find the visitor's priority */
    return priority;                                                                    /* Return the visitor's priority */
  }

  public final boolean Get_status() {                                            /* Find if the visitor is served or not */
    return ready_to_leave;                                                                /* Return the visitor's status */
  }

  public final Guest Get_guest() {                                                           /* Find the visitor's guest */
    return guest;                                                                          /* Return the visitor's guest */
  }

  public final boolean Has_guest() {                                           /* Find if the visitor has a guest or not */
    return (guest != null);                                      /* If guest points to someone, then visitor has a guest */
  }
}
