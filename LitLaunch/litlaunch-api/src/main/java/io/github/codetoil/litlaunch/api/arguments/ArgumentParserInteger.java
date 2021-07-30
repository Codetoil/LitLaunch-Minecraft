package io.github.codetoil.litlaunch.api.arguments;

public class ArgumentParserInteger implements IArgumentParser<IArgumentValue<Integer>> {
    @Override
    public ArgumentValue<Integer> parse(String input) {
        if (input.startsWith("#")) {
            input = input.substring(1);
        }  //else LaunchCommon.getFrontEnd().DO_THING_OBJECT().notifyUser("[" + LaunchCommon.NAME + " v" + LaunchCommon.VERSION + "] Warning: Please precede all integers with a `#` sign to make sure it registers as an integer", IDoThing.Color.YELLOW);

        //LaunchCommon.getFrontEnd().verbose("input: " + input);
        Integer int_ = Integer.parseInt(input);
        //LaunchCommon.getFrontEnd().verbose("int_: " + input);
        //LaunchCommon.getFrontEnd().verbose("Int_:" + Int_);
        //LaunchCommon.getFrontEnd().verbose("Int_.value:" + Int_.value);
        return new ArgumentValue<>(int_, this);
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public Class<?> getClassOfT() {
        return Integer.class;
    }
}