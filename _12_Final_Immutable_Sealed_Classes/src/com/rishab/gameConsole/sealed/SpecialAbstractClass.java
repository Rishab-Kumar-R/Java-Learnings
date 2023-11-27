package com.rishab.gameConsole.sealed;

// if we are using nested classes, then permit clause is not required
public sealed abstract class SpecialAbstractClass permits FinalKid, NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    final class Kid extends SpecialAbstractClass {
    }
}
