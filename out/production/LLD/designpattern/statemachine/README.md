# State Pattern (State Machine) - Memory Guide

## Quick Diagram (ASCII)
```
   [NoCoin]
      | insertCoin
      v
   [HasCoin]
      | selectProduct
      v
   [Dispense]
      | dispense
      v
   [NoCoin]
```

## Remember the Syntax (Java)
Think of it as **Context + State interface + Concrete States**.

1. `State` interface declares actions (same methods as Context exposes).
2. Each `ConcreteState` implements those actions and **decides the next state**.
3. `Context` holds a `State` reference and **delegates** calls to it.

### Skeleton (memorize this shape)
```
interface State {
  void action1(Context c);
  void action2(Context c);
}

class ConcreteStateA implements State {
  public void action1(Context c) { c.setState(new ConcreteStateB()); }
  public void action2(Context c) { /* invalid */ }
}

class ConcreteStateB implements State {
  public void action1(Context c) { /* invalid */ }
  public void action2(Context c) { c.setState(new ConcreteStateA()); }
}

class Context {
  private State state = new ConcreteStateA();

  void action1() { state.action1(this); }
  void action2() { state.action2(this); }

  void setState(State s) { this.state = s; }
}
```

## One-Liner Memory Hook
"**Context delegates, State decides next.**"

## Files in this package
- `VendingMachineState.java` (State interface)
- `NoCoinState.java`
- `HasCoinState.java`
- `DispenseState.java`
- `VendingMachine.java` (Context)
- `StateMachineMain.java` (demo)
