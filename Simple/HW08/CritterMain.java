public class CritterMain {
    public static void main(String[] args) {
        CritterFrame frame = new CritterFrame(60, 40);

        //uncomment each of these lines as you complete these classes
         frame.add(30, Bear.class);
         frame.add(30, Lion.class);
         frame.add(30, Giant.class);
         frame.add(30, DrewBeforeHusky.class);

         frame.add(30, FlyTrap.class);
         frame.add(30, Food.class);

        frame.start();
    }
}
