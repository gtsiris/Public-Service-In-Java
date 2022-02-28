package public_service;

public final class Entrance extends Unlimited_Space {
  private final Visitor_List list;  /* Includes the visitors that are currently in this entrace */
  
  public Entrance() {
                                     /* Default initialization of its nature as unlimited space */
    list = new Visitor_List();                                         /* Create a visitor list */
    System.out.println("The Entrance has been created!");
  }

  public final Visitor_List Get_list() {                        /* Get the list of the entrance */
    return list;                                             /* Return the list of the entrance */
  }

  public final void Enter(Visitor visitor) {                   /* A visitor enters the entrance */
    this.Somebody_Enters();       /* Increase by 1 the current number of people in the entrance */
    if (visitor.Has_guest())                                          /* If visitor has a guest */
      this.Enter(visitor.Get_guest());                   /* Visitor's guest enters the entrance */
    list.Add_Visitor(visitor);                   /* Add the visitor to the list of the entrance */
  }

  public final void Enter(final Guest guest) {                   /* A guest enters the entrance */
    this.Somebody_Enters();       /* Increase by 1 the current number of people in the entrance */
  }

  public final void Exit(final Visitor visitor) {               /* A visitor exits the entrance */
    this.Somebody_Exits();        /* Decrease by 1 the current number of people in the entrance */
    if (visitor.Has_guest())                                          /* If visitor has a guest */
      this.Exit(visitor.Get_guest());                     /* Visitor's guest exits the entrance */
    list.Exclude_This_Visitor(visitor);      /* Erase the visitor from the list of the entrance */
  }

  public final void Exit(final Guest guest) {                     /* A guest exits the entrance */
    this.Somebody_Exits();        /* Decrease by 1 the current number of people in the entrance */
  }
}
