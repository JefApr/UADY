import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Level {
    private String name;
    private String IDLevel;
    private ArrayList<Room> rooms;

    public Level(String IDLevel, String name, ArrayList<Room> rooms) {
        this.IDLevel = IDLevel;
        this.name = name;
        this.rooms = rooms;
    }

    public Level(String name) {
        this.name = name;
        this.rooms = new ArrayList<Room>();
    }

    public int getCounterRooms(ArrayList<Room> rooms) {
        return rooms.size();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getRoomCounter() {
        return rooms.size();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int searchRoom(Room otherRoom) {
        int index = 0;
        boolean flag = false;

        for (index = 0; index < rooms.size() && flag == false; index++) {
            if (rooms.get(index).equals(otherRoom)) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            index = -1;
        }
        return index;
    }

    public boolean RemoveRoom(Room room) {
        boolean flag = false;
        int pos = searchRoom(room);
        if (pos != -1) {
            rooms.remove(pos);
        }

        return flag;
    }

    public String toString() {
        String output = "";
        output = output + name + "\n";

        for (int i = 0; i < rooms.size(); i++) {
            output = output + rooms.get(i).toString() + "\n";
        }
        return output;
    }

    public boolean switchAllOffRooms() {
        for (int index = 0; index < getRoomCounter(); index++) {
            rooms.get(index).switchOffAllDevices();
        }
        return true;
    }

    public boolean switchAllOnRooms() {
        for (int index = 0; index < getRoomCounter(); index++) {
            rooms.get(index).switchOnAllDevices();
        }
        return true;
    }

    public boolean switchOnRoom(Room room) {
        boolean flag = false;
        int index;
        index = this.searchRoom(room);
        if (index > -1) {
            rooms.get(index).switchOnAllDevices();
            flag = true;
        }
        return flag;
    }

    public boolean switchOffRoom(Room room) {
        boolean flag = false;
        int index;
        index = this.searchRoom(room);
        if (index > -1) {
            rooms.get(index).switchOffAllDevices();
            flag = true;
        }
        return flag;
    }

    public boolean levelSwitchOffDevice(Room room, Device device) {
        boolean found = false;
        int lRoom = this.searchRoom(room);
        if (lRoom > -1) {
            ArrayList<Device> d;
            d = rooms.get(lRoom).getDevices();
            int lDevice = rooms.get(lRoom).searchDevice(device);

            if (lDevice > -1)
                d.get(lDevice).switchOffDevice();
            else
                found = true;
        }
        return found;
    }

    public boolean levelSwitchOnDevice(Room room, Device device) {
        boolean found = false;
        int lRoom = this.searchRoom(room);
        if (lRoom > -1) {
            ArrayList<Device> d;
            d = rooms.get(lRoom).getDevices();
            int lDevice = rooms.get(lRoom).searchDevice(device);

            if (lDevice > -1)
                d.get(lDevice).switchOnDevice();
            else
                found = true;
        }
        return found;
    }

    public void switchAllOffSameDevices(String nameDevices) {// Nuevo
        for (int i = 0; i < rooms.size(); i++) {
            ArrayList<Device> devices = rooms.get(i).getDevices();
            for (int j = 0; j < rooms.get(i).getDeviceCounter(); j++) {
                if (devices.get(j).getName().equals(nameDevices)) {
                    devices.get(j).switchOffDevice();
                }
            }
        }
    }

    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj instanceof Level && obj != null) {
            Level otherDevice = (Level) obj;
            if (this.name == otherDevice.name) {
                flag = true;
            }
        }
        return flag;
    }

}
