package com.walterjwhite.examples.practice.problems.strings;

public class StringReverser {
  //    public static void main(final String[] arguments){
  //        System.out.println("reversed:" + reverse("abcdefg"));
  //        System.out.println("reversed:" + reverse("tiger"));
  //    }

  public static String reverse(final String input) {
    if (input == null || input.length() == 1) return input;

    return input.charAt(input.length() - 1) + reverse(input.substring(0, input.length() - 1));
  }
}
