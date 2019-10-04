import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Room {
   
    private ArrayList<Device> devices;
    private String IDRoom;
    private String name;

    public Room(String IDRoom, String name, ArrayList<Device> devices) {
        this.IDRoom = IDRoom;
        this.name = name;
        this.devices = devices;
    }

    public Room(String name) {
        this.devices = new ArrayList<Device>();
        this.name = name;

    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(Device[] devices) {
        this.setDevices(devices);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        String output = "";
        output = output + name + "\n";
        for (int i = 0; i < this.devices.size(); i++) {
            output = output + devices.get(i).toString() + "\n";
        }
        return output;
    }

    public int getDeviceCounter() {
        return devices.size();
    }

    public int searchDevice(Device otherDevice) {
        int index = 0;
        boolean flag = false;
        for (index = 0; index < getDeviceCounter() && flag == false; index++) {
            if (devices.get(index).equals(otherDevice)) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            index = -1;
        }
        return index;
    }

    public boolean removeDevice(Device device) {
        boolean flag = false;
        int pos = searchDevice(device);

        if (pos != -1) {
            devices.remove(pos);
        }

        return flag;

    }

    public void switchOffAllDevices() {
        for (int index = 0; index < getDeviceCounter(); index++) {
            devices.get(index).switchOffDevice();
        }
    }

    public void switchOnAllDevices() {
        for (int index = 0; index < getDeviceCounter(); index++) {
            devices.get(index).switchOnDevice();
        }
    }

    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj instanceof Room && obj != null) {
            Room otherRoom = (Room) obj;
            if (this.name == otherRoom.name) {
                flag = true;
            }
        }
        return flag;
    }
}
