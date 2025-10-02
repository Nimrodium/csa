public class Phones {

    static class SmartPhone {

        // stating attribute name and data type is not necessary as they are already defined in code.
        String model;
        // imporant so that each smartphone can have an identifying name, like "iPhone 16" and "Pixel 9 Pro".

        String os;
        // important to distinguish the os and its version using a unique identifier. such as
        // "Android-v16-Linux-v6.1-aarch64", "iOSv25.0-XNUv25.0-aarch64", or "PMOS-v25.12Linux-Alpine-aarch64"

        int batteryLevel;
        // important so that programs and users can be aware of the current battery level
        boolean isPoweredOn;
        // important for waking the os from a cold state and displaying the graphical interface
        boolean isCharging;
        // important for indicating if special dev features should be granted to the user
        boolean isDev;

        // getters
        public String getModel() {
            return model;
        }

        public String getOs() {
            return os;
        }

        public int getBatteryLevel() {
            return batteryLevel;
        }

        public boolean isPoweredOn() {
            return isPoweredOn;
        }

        public boolean isCharging() {
            return isCharging;
        }

        public boolean isDev() {
            return isDev;
        }

        // setters
        public void setBatteryLevel(int percent) {
            this.batteryLevel = percent;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public void setPoweredOn(boolean isPoweredOn) {
            this.isPoweredOn = isPoweredOn;
        }

        public void setCharging(boolean isCharging) {
            this.isCharging = isCharging;
        }

        public void setDev(boolean isDev) {
            this.isDev = isDev;
        }

        // these are important as modifying object fields directly can sometimes be dangerous
        // if their state is tied an invariant assertion and setting them directly without logic to also change or throw an error
        // when breaking the invariant

        // and depending on scope the object fields may not be publically accessible.

        // reboot the device
        public void reboot() {
            System.out.println("reboot stub");
        }

        // reboot device into a minimal recovery environment
        public void rebootRecoveryMode() {
            System.out.println("reboot stub");
        }

        // installs an app to the phone
        public void installApp(String applicationPackageID) {
            System.out.println("installing " + applicationPackageID);
        }

        // takes a photo
        public void takePhoto(String path) {
            System.out.println("taking photo and saving to " + path);
        }

        // gives developers access a privledged environment, blocks regular users
        public void getRootShell() {
            if (this.isDev) {
                System.out.println("root user login");
            } else {
                System.out.println(
                    "access denied, user does not have root access"
                );
            }
        }
    }
}
