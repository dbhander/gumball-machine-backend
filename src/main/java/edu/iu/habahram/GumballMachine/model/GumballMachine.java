package edu.iu.habahram.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    @Override
    public TransitionResult insertQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "You can't insert another quarter";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            state = HAS_QUARTER;
            message = "You inserted a quarter";
            succeeded = true;
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "You can't insert a quarter, the machine is sold out";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "Please wait, we're already giving you a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult ejectQuarter() {
        boolean succeeded = false;
        String message = "";
        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            System.out.println("Inside Has quarter");
            message = "You ejected a quarter";
            succeeded = true;
            state = NO_QUARTER;
        } else if(state.equalsIgnoreCase(SOLD)){
            message = "You can't eject a quarter, the machine has no quarter";
        } else if(state.equalsIgnoreCase(SOLD_OUT)){
            message = "You can't eject a quarter, the machine has no gumball";
        } else{
            message = "First, you have to insert a quarter in order to eject a quarter";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public TransitionResult turnCrank() {
        boolean succeeded = false;
        String message = "";

        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            state = SOLD;
            return this.dispense();
        } else if(state.equalsIgnoreCase(SOLD)){
            message = "You have to pay first to get a gumball";
        } else if(state.equalsIgnoreCase(SOLD_OUT)){
            message = "You can't get gumball, the machine has no gumball";
        } else{
            message = "First, you have to insert a quarter in order to get a gumball";
        }
        return new TransitionResult(succeeded, message, state, count);
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {

    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public TransitionResult dispense() {
        boolean succeeded = false;
        String message = "";

        if (state.equalsIgnoreCase(HAS_QUARTER)) {
            message = "First, you have to crank gumball machine";
        } else if (state.equalsIgnoreCase(NO_QUARTER)) {
            message = "You have to pay first";
        } else if (state.equalsIgnoreCase(SOLD_OUT)) {
            message = "There is no gumball in the machine";
        } else if (state.equalsIgnoreCase(SOLD)) {
            message = "We're dispensing a gumball";
            this.count -= 1;
            succeeded = true;
        }
        return new TransitionResult(succeeded, message, state, count);
    }


}