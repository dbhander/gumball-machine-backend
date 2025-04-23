package edu.iu.habahram.GumballMachine.model;

import edu.iu.habahram.GumballMachine.repository.IGumballRepository;

public class SoldOutState implements IState{
    IGumballMachine gumballMachine;
    public SoldOutState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        return new TransitionResult(false, "You can't insert a quarter, the machine is sold out", gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult ejectQuarter() {
        return new TransitionResult(false, "You can't eject, you haven't inserted a quarter yet", gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult turnCrank() {
        return new TransitionResult(false, "You turned, but there are no gumballs", gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public TransitionResult dispense() {
        return new TransitionResult(false, "No gumball dispensed", gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

    @Override
    public String getTheName() {
        return GumballMachineState.OUT_OF_GUMBALLS.name();
    }

    @Override
    public TransitionResult refill(int count) {
        gumballMachine.setCount(gumballMachine.getCount() + count);
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        return new TransitionResult(true, "Machine refilled", gumballMachine.getTheStateName(), gumballMachine.getCount());
    }

}
