package public_service;

public final class Visitor_List {
  private final class Visitor_List_Node {
    private final Visitor v;                                                                     /* Reference to the current visitor */
    private Visitor_List_Node next_visitor;                                  /* Reference to the next node in the list, if it exists */
    
    private Visitor_List_Node(Visitor visitor) {
      v = visitor;
    }
  }
  
  private Visitor_List_Node head;                                        /* Reference to the node of the first visitor, if it exists */

  public final boolean Is_Empty() {                                                         /* Determine if the list is empty or not */
     return (head == null);                                                 /* If there is no visitor in this list, then it is empty */
  }

  public final int Size() {                                                             /* Count the number of visitors in this list */
    int size = 0;
    Visitor_List_Node temp = head;                                                                   /* Starting from the first node */
    while (temp != null) {                                                                       /* Repeat until the end of the list */
      size++;                                                                                           /* Increase the counter by 1 */
      temp = temp.next_visitor;                                                                               /* Go to the next node */
    }
    return size;                                                                                  /* Return the value of the counter */
  }

  public final void Add_Visitor(Visitor visitor) {                              /* Add the visitor to the list according to priority */
    if (this.Is_Empty()) {                                                                   /* If there is no visitors in this list */
      head = new Visitor_List_Node(visitor);                                           /* Create a new node at the start of the list */
      return;
    }
                                                                             /* Else if there are already other visitors in the list */
    Visitor_List_Node temp = new Visitor_List_Node(visitor);                                                    /* Create a new node */
    if (visitor.Get_priority() < head.v.Get_priority()) {                            /* If the visitor is prior to the first visitor */
      temp.next_visitor = head;                                                          /* Add the visitor at the start of the list */
      head = temp;                                                                                   /* Update the start of the list */
      return;
    }
    if (head.next_visitor == null && head.v.Get_priority() < visitor.Get_priority()) {  
                                                          /* If there is only one visitor in the list and he is prior to the visitor */
      head.next_visitor = temp;                                                                         /* Add the visitor after him */
      return;
    }
    else if (head.v.Get_priority() < visitor.Get_priority() && visitor.Get_priority() < (head.next_visitor.v).Get_priority()) {
                                                 /* If the first visitor is prior and the second visitor is posterior to the visitor */
        temp.next_visitor = head.next_visitor;                                                       /* Add the visitor between them */
        head.next_visitor = temp;                                                                    /* Update the first node's link */
        return;
    }
    Visitor_List_Node temp1 = head.next_visitor;                                                    /* Starting from the second node */
    while (temp1.next_visitor != null) {                                            /* Repeat unless it is the last node of the list */
      if (temp1.v.Get_priority() < visitor.Get_priority() && visitor.Get_priority() < temp1.next_visitor.v.Get_priority()) {
                                                               /* If there is a visitor prior and a visitor posterior to the visitor */
        temp.next_visitor = temp1.next_visitor;                                                      /* Add the visitor between them */
        break;
      }
      temp1 = temp1.next_visitor;                                                                             /* Go to the next node */
    }
    temp1.next_visitor = temp;                                                                       /* Update the prior node's link */
  }

  public final void Add_Visitor_To_The_End(Visitor visitor) {                              /* Add the visitor at the end of the list */
    if (this.Is_Empty()) {                                                                    /* If there is no visitor in this list */
      head = new Visitor_List_Node(visitor);                                           /* Create a new node at the start of the list */
    }
    else {
      Visitor_List_Node temp = head;                                                                 /* Starting from the first node */
      while (temp.next_visitor != null)                                             /* Repeat unless it is the last node of the list */
        temp = temp.next_visitor;                                                                                 /* Go to next node */
      temp.next_visitor = new Visitor_List_Node(visitor);                                   /* Create a new node after the last node */
    }
  }

  public final void Exclude_This_Visitor(final Visitor visitor) {                                /* Erase this visitor from the list */
    Visitor_List_Node temp0 = head, temp1 = head.next_visitor;
    if (temp0.v == visitor) {                                                                    /* If this visitor is the first one */
      head = temp1;                                           /* Update the start of the list using the reference to the second node */
      return;
    }
    while (!(temp1.v == visitor)) {                                                             /* Repeat until you find the visitor */
      temp0 = temp1;                                                                                 /* Keep a reference to the node */
      temp1 = temp1.next_visitor;                                                                             /* Go to the next node */
    }
    temp0.next_visitor = temp1.next_visitor;                               /* Skip this node using the reference to its previous one */
  }

  public final Visitor Exclude_ith_Visitor(final int i) {                                     /* Erase the ith visitor from the list */
    Visitor_List_Node temp0 = head, temp1 = head.next_visitor;
    int pos = 1;
    if (i == 1) {                                                                    /* In case we want to exclude the first visitor */
      Visitor visitor = new Visitor(temp0.v);                         /* Keep a reference to the first visitor in order to return it */
      head = temp1;                                           /* Update the start of the list using the reference to the second node */
      return visitor;                                                                                    /* Return the first visitor */
    }
    while (++pos < i) {                                                                    /* Repeat untill you find the ith visitor */
      temp0 = temp1;                                                                                 /* Keep a reference to the node */
      temp1 = temp1.next_visitor;                                                                             /* Go to the next node */
    }
    Visitor visitor = new Visitor(temp1.v);                             /* Keep a reference to the ith visitor in order to return it */
    temp0.next_visitor = temp1.next_visitor;                            /* Skip the ith node using the reference to its previous one */
    return visitor;                                                                                        /* Return the ith visitor */
  }

  public final Visitor Get_ith_Visitor(final int i) {                                            /* Find the ith visitor of the list */
    Visitor_List_Node temp = head;                                                                   /* Starting from the first node */
    int pos = 1;
    while (pos++ < i) {                                                                    /* Repeat untill you find the ith visitor */
      temp = temp.next_visitor;                                                                               /* Go to the next node */
    }
    return temp.v;                                                                                         /* Return the ith visitor */
  }

  public final boolean More_Visitors_With_This_Destination_Office(final int office_number) {
                                                /* Determine if there are visitors in this list that would like to go to this office */
    Visitor_List_Node temp = head;                                                                   /* Starting from the first node */
    while (temp != null) {                                                                       /* Repeat until the end of the list */
      if (temp.v.Get_dest_office() == office_number)                              /* If this visitor would like to go to this office */
        return true;                                                      /* Then atleast one visitor has this office as destination */
      temp = temp.next_visitor;                                                                                   /* Go to next node */
    }
    return false;                                              /* No visitor found in this list that would like to go to this office */
  }
  
  public final Visitor Exclude_Visitor_With_This_Destination_Office(final int office_number) {
                                                         /* Find the first visitor of this list that would like to go to this office */
    Visitor_List_Node temp0 = head, temp1 = head.next_visitor;
    if (temp0.v.Get_dest_office() == office_number) {                         /* If the first visitor has this office as destination */
      Visitor visitor = new Visitor(temp0.v);                             /* Keep a reference to first visitor in order to return it */
      head = temp1;                                                                                  /* Update the start of the list */
      return visitor;                                                                                    /* Return the first visitor */
    }
    while (temp1.v.Get_dest_office() != office_number) {  /* Find the first visitor in the list that would like to go in this office */
      temp0 = temp1;                                                                                 /* Keep a reference to the node */
      temp1 = temp1.next_visitor;                                                                             /* Go to the next node */
    }
    Visitor visitor = new Visitor(temp1.v);                                /* Keep a reference to this visitor in order to return it */
    temp0.next_visitor = temp1.next_visitor;                               /* Skip this node using the reference to its previous one */
    return visitor;                                                                                           /* Return this visitor */
  }
  
  public final boolean More_Visitors_Ready_To_Leave() {                       /* Determine if there are served visitors in this list */
    Visitor_List_Node temp = head;                                                                   /* Starting from the first node */
    while (temp != null) {                                                                       /* Repeat until the end of the list */
      if (temp.v.Get_status())                                                                       /* If a served visitor is found */
        return true;                                                     /* There is atleast one visitor in this list ready to leave */
      temp = temp.next_visitor;                                                                               /* Go to the next node */
    }
    return false;                                                                         /* There is no served visitor in this list */
  }

  public final Visitor Get_Visitor_Ready_To_Leave() {                                  /* Find the first served visitor of this list */
    Visitor_List_Node temp = head;                                                                   /* Starting from the first node */
    while (temp != null) {                                                                       /* Repeat until the end of the list */
      if (temp.v.Get_status())                                                                       /* If a served visitor is found */
        return temp.v;                                                                                        /* Return this visitor */
      temp = temp.next_visitor;                                                                               /* Go to the next node */
    }
    return null;
  }
}
