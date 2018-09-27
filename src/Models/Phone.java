package Models;

import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class Phone {
    private String make;
    private String model, os;
    private double screenSize, memory, frontCameraRes, rearCameraRes, price;
    private Image image;

    public Phone(String make, String model, String os, double screenSize) {
        setMake(make);
        setModel(model);
        setOs(os);
        setScreenSize(screenSize);
    }

    public Phone(String make, String model, String os, double screenSize, double memory, double frontCameraRes, double rearCameraRes, double price, Image image) {
        this(make, model, os, screenSize);
        setMemory(memory);
        setFrontCameraRes(frontCameraRes);
        setRearCameraRes(rearCameraRes);
        setPrice(price);
        setImage(image);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        ArrayList<String> validMakes = null;
        try {
            validMakes = DBConnect.getPhoneManufacturers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (validMakes.contains(make))
            this.make = make;
        else
            throw new IllegalArgumentException("Make must be one of "+ validMakes);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (!model.isEmpty() && model.length() <30)
            this.model = model;
        throw new IllegalArgumentException("Model name must be 0-30 characters");
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        if (os.equals("iOS") || os.equals("Android") || os.equals("Blackberry"))
            this.os = os;
        else
            throw new IllegalArgumentException("OS must be iOS, Android or Blackberry");
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        if (screenSize>0 && screenSize<1000)
            this.screenSize = screenSize;
        else
            throw new IllegalArgumentException("screen size must be 0-1000");
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        if (memory>0 && memory<=512)
            this.memory = memory;
        else
            throw new IllegalArgumentException("Memory must be 0-512");
    }

    public double getFrontCameraRes() {
        return frontCameraRes;
    }

    public void setFrontCameraRes(double frontCameraRes) {
        if (frontCameraRes>=0 && frontCameraRes<100)
            this.frontCameraRes = frontCameraRes;
        else
            throw new IllegalArgumentException("Front Camera resolution should be 0-100");
    }

    public double getRearCameraRes() {
        return rearCameraRes;
    }

    public void setRearCameraRes(double rearCameraRes) {
        if (rearCameraRes>=0 && rearCameraRes<=100)
            this.rearCameraRes = rearCameraRes;
        else
            throw new IllegalArgumentException("Rear camera Resolution must be 0-100");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price>=0 && price < 10000)
            this.price = price;
        else
            throw new IllegalArgumentException("Price must be 0-10000");
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
