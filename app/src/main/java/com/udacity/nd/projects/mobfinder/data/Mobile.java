package com.udacity.nd.projects.mobfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Mobile implements Parcelable {

    private String imageURL;

    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("technology")
    @Expose
    private String technology;
    @SerializedName("gprs")
    @Expose
    private String gprs;
    @SerializedName("edge")
    @Expose
    private String edge;
    @SerializedName("announced")
    @Expose
    private String announced;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("dimensions")
    @Expose
    private String dimensions;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("sim")
    @Expose
    private String sim;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("resolution")
    @Expose
    private String resolution;
    @SerializedName("display_c")
    @Expose
    private String displayC;
    @SerializedName("card_slot")
    @Expose
    private String cardSlot;
    @SerializedName("alert_types")
    @Expose
    private String alertTypes;
    @SerializedName("sound_c")
    @Expose
    private String soundC;
    @SerializedName("wlan")
    @Expose
    private String wlan;
    @SerializedName("bluetooth")
    @Expose
    private String bluetooth;
    @SerializedName("gps")
    @Expose
    private String gps;
    @SerializedName("radio")
    @Expose
    private String radio;
    @SerializedName("usb")
    @Expose
    private String usb;
    @SerializedName("messaging")
    @Expose
    private String messaging;
    @SerializedName("browser")
    @Expose
    private String browser;
    @SerializedName("java")
    @Expose
    private String java;
    @SerializedName("features_c")
    @Expose
    private String featuresC;
    @SerializedName("battery_c")
    @Expose
    private String batteryC;
    @SerializedName("colors")
    @Expose
    private String colors;
    @SerializedName("sensors")
    @Expose
    private String sensors;
    @SerializedName("cpu")
    @Expose
    private String cpu;
    @SerializedName("internal")
    @Expose
    private String internal;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("body_c")
    @Expose
    private String bodyC;
    @SerializedName("primary_")
    @Expose
    private String primary;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("secondary")
    @Expose
    private String secondary;
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("network_c")
    @Expose
    private String networkC;
    @SerializedName("chipset")
    @Expose
    private String chipset;
    @SerializedName("features")
    @Expose
    private String features;
    @SerializedName("protection")
    @Expose
    private String protection;
    @SerializedName("gpu")
    @Expose
    private String gpu;
    @SerializedName("multitouch")
    @Expose
    private String multitouch;
    @SerializedName("loudspeaker")
    @Expose
    private String loudspeaker;
    @SerializedName("audio_quality")
    @Expose
    private String audioQuality;
    @SerializedName("nfc")
    @Expose
    private String nfc;
    @SerializedName("camera")
    @Expose
    private String camera;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("performance")
    @Expose
    private String performance;
    @SerializedName("build")
    @Expose
    private String build;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("_2g_bands")
    @Expose
    private String _2gBands;
    @SerializedName("_3_5mm_jack_")
    @Expose
    private String _35mmJack;
    @SerializedName("_3g_bands")
    @Expose
    private String _3gBands;
    @SerializedName("_4g_bands")
    @Expose
    private String _4gBands;
    public final static Parcelable.Creator<Mobile> CREATOR = new Creator<Mobile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Mobile createFromParcel(Parcel in) {
            return new Mobile(in);
        }

        public Mobile[] newArray(int size) {
            return (new Mobile[size]);
        }

    };

    protected Mobile(Parcel in) {
        this.deviceName = ((String) in.readValue((String.class.getClassLoader())));
        this.brand = ((String) in.readValue((String.class.getClassLoader())));
        this.technology = ((String) in.readValue((String.class.getClassLoader())));
        this.gprs = ((String) in.readValue((String.class.getClassLoader())));
        this.edge = ((String) in.readValue((String.class.getClassLoader())));
        this.announced = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.dimensions = ((String) in.readValue((String.class.getClassLoader())));
        this.weight = ((String) in.readValue((String.class.getClassLoader())));
        this.sim = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.size = ((String) in.readValue((String.class.getClassLoader())));
        this.resolution = ((String) in.readValue((String.class.getClassLoader())));
        this.displayC = ((String) in.readValue((String.class.getClassLoader())));
        this.cardSlot = ((String) in.readValue((String.class.getClassLoader())));
        this.alertTypes = ((String) in.readValue((String.class.getClassLoader())));
        this.soundC = ((String) in.readValue((String.class.getClassLoader())));
        this.wlan = ((String) in.readValue((String.class.getClassLoader())));
        this.bluetooth = ((String) in.readValue((String.class.getClassLoader())));
        this.gps = ((String) in.readValue((String.class.getClassLoader())));
        this.radio = ((String) in.readValue((String.class.getClassLoader())));
        this.usb = ((String) in.readValue((String.class.getClassLoader())));
        this.messaging = ((String) in.readValue((String.class.getClassLoader())));
        this.browser = ((String) in.readValue((String.class.getClassLoader())));
        this.java = ((String) in.readValue((String.class.getClassLoader())));
        this.featuresC = ((String) in.readValue((String.class.getClassLoader())));
        this.batteryC = ((String) in.readValue((String.class.getClassLoader())));
        this.colors = ((String) in.readValue((String.class.getClassLoader())));
        this.sensors = ((String) in.readValue((String.class.getClassLoader())));
        this.cpu = ((String) in.readValue((String.class.getClassLoader())));
        this.internal = ((String) in.readValue((String.class.getClassLoader())));
        this.os = ((String) in.readValue((String.class.getClassLoader())));
        this.bodyC = ((String) in.readValue((String.class.getClassLoader())));
        this.primary = ((String) in.readValue((String.class.getClassLoader())));
        this.video = ((String) in.readValue((String.class.getClassLoader())));
        this.secondary = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.networkC = ((String) in.readValue((String.class.getClassLoader())));
        this.chipset = ((String) in.readValue((String.class.getClassLoader())));
        this.features = ((String) in.readValue((String.class.getClassLoader())));
        this.protection = ((String) in.readValue((String.class.getClassLoader())));
        this.gpu = ((String) in.readValue((String.class.getClassLoader())));
        this.multitouch = ((String) in.readValue((String.class.getClassLoader())));
        this.loudspeaker = ((String) in.readValue((String.class.getClassLoader())));
        this.audioQuality = ((String) in.readValue((String.class.getClassLoader())));
        this.nfc = ((String) in.readValue((String.class.getClassLoader())));
        this.camera = ((String) in.readValue((String.class.getClassLoader())));
        this.display = ((String) in.readValue((String.class.getClassLoader())));
        this.performance = ((String) in.readValue((String.class.getClassLoader())));
        this.build = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this._2gBands = ((String) in.readValue((String.class.getClassLoader())));
        this._35mmJack = ((String) in.readValue((String.class.getClassLoader())));
        this._3gBands = ((String) in.readValue((String.class.getClassLoader())));
        this._4gBands = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Mobile() {
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public String getAnnounced() {
        return announced;
    }

    public void setAnnounced(String announced) {
        this.announced = announced;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDisplayC() {
        return displayC;
    }

    public void setDisplayC(String displayC) {
        this.displayC = displayC;
    }

    public String getCardSlot() {
        return cardSlot;
    }

    public void setCardSlot(String cardSlot) {
        this.cardSlot = cardSlot;
    }

    public String getAlertTypes() {
        return alertTypes;
    }

    public void setAlertTypes(String alertTypes) {
        this.alertTypes = alertTypes;
    }

    public String getSoundC() {
        return soundC;
    }

    public void setSoundC(String soundC) {
        this.soundC = soundC;
    }

    public String getWlan() {
        return wlan;
    }

    public void setWlan(String wlan) {
        this.wlan = wlan;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public String getMessaging() {
        return messaging;
    }

    public void setMessaging(String messaging) {
        this.messaging = messaging;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getFeaturesC() {
        return featuresC;
    }

    public void setFeaturesC(String featuresC) {
        this.featuresC = featuresC;
    }

    public String getBatteryC() {
        return batteryC;
    }

    public void setBatteryC(String batteryC) {
        this.batteryC = batteryC;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSensors() {
        return sensors;
    }

    public void setSensors(String sensors) {
        this.sensors = sensors;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBodyC() {
        return bodyC;
    }

    public void setBodyC(String bodyC) {
        this.bodyC = bodyC;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getNetworkC() {
        return networkC;
    }

    public void setNetworkC(String networkC) {
        this.networkC = networkC;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMultitouch() {
        return multitouch;
    }

    public void setMultitouch(String multitouch) {
        this.multitouch = multitouch;
    }

    public String getLoudspeaker() {
        return loudspeaker;
    }

    public void setLoudspeaker(String loudspeaker) {
        this.loudspeaker = loudspeaker;
    }

    public String getAudioQuality() {
        return audioQuality;
    }

    public void setAudioQuality(String audioQuality) {
        this.audioQuality = audioQuality;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String get2gBands() {
        return _2gBands;
    }

    public void set2gBands(String _2gBands) {
        this._2gBands = _2gBands;
    }

    public String get35mmJack() {
        return _35mmJack;
    }

    public void set35mmJack(String _35mmJack) {
        this._35mmJack = _35mmJack;
    }

    public String get3gBands() {
        return _3gBands;
    }

    public void set3gBands(String _3gBands) {
        this._3gBands = _3gBands;
    }

    public String get4gBands() {
        return _4gBands;
    }

    public void set4gBands(String _4gBands) {
        this._4gBands = _4gBands;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("deviceName", deviceName).append("brand", brand).append("technology", technology).append("gprs", gprs).append("edge", edge).append("announced", announced).append("status", status).append("dimensions", dimensions).append("weight", weight).append("sim", sim).append("type", type).append("size", size).append("resolution", resolution).append("displayC", displayC).append("cardSlot", cardSlot).append("alertTypes", alertTypes).append("soundC", soundC).append("wlan", wlan).append("bluetooth", bluetooth).append("gps", gps).append("radio", radio).append("usb", usb).append("messaging", messaging).append("browser", browser).append("java", java).append("featuresC", featuresC).append("batteryC", batteryC).append("colors", colors).append("sensors", sensors).append("cpu", cpu).append("internal", internal).append("os", os).append("bodyC", bodyC).append("primary", primary).append("video", video).append("secondary", secondary).append("speed", speed).append("networkC", networkC).append("chipset", chipset).append("features", features).append("protection", protection).append("gpu", gpu).append("multitouch", multitouch).append("loudspeaker", loudspeaker).append("audioQuality", audioQuality).append("nfc", nfc).append("camera", camera).append("display", display).append("performance", performance).append("build", build).append("price", price).append("_2gBands", _2gBands).append("_35mmJack", _35mmJack).append("_3gBands", _3gBands).append("_4gBands", _4gBands).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(deviceName);
        dest.writeValue(brand);
        dest.writeValue(technology);
        dest.writeValue(gprs);
        dest.writeValue(edge);
        dest.writeValue(announced);
        dest.writeValue(status);
        dest.writeValue(dimensions);
        dest.writeValue(weight);
        dest.writeValue(sim);
        dest.writeValue(type);
        dest.writeValue(size);
        dest.writeValue(resolution);
        dest.writeValue(displayC);
        dest.writeValue(cardSlot);
        dest.writeValue(alertTypes);
        dest.writeValue(soundC);
        dest.writeValue(wlan);
        dest.writeValue(bluetooth);
        dest.writeValue(gps);
        dest.writeValue(radio);
        dest.writeValue(usb);
        dest.writeValue(messaging);
        dest.writeValue(browser);
        dest.writeValue(java);
        dest.writeValue(featuresC);
        dest.writeValue(batteryC);
        dest.writeValue(colors);
        dest.writeValue(sensors);
        dest.writeValue(cpu);
        dest.writeValue(internal);
        dest.writeValue(os);
        dest.writeValue(bodyC);
        dest.writeValue(primary);
        dest.writeValue(video);
        dest.writeValue(secondary);
        dest.writeValue(speed);
        dest.writeValue(networkC);
        dest.writeValue(chipset);
        dest.writeValue(features);
        dest.writeValue(protection);
        dest.writeValue(gpu);
        dest.writeValue(multitouch);
        dest.writeValue(loudspeaker);
        dest.writeValue(audioQuality);
        dest.writeValue(nfc);
        dest.writeValue(camera);
        dest.writeValue(display);
        dest.writeValue(performance);
        dest.writeValue(build);
        dest.writeValue(price);
        dest.writeValue(_2gBands);
        dest.writeValue(_35mmJack);
        dest.writeValue(_3gBands);
        dest.writeValue(_4gBands);
    }

    public int describeContents() {
        return 0;
    }

}