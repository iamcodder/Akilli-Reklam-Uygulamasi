package com.patronusstudio.akillireklam;

import android.os.Parcel;
import android.os.Parcelable;

public class magazaModel implements Parcelable {

    private String FirmaID;
    private String FirmaAdi;
    private String Lokasyon;
    private String KampanyaIcerik;
    private String KampanyaSuresi;

    public magazaModel() {
    }

    public magazaModel(String firmaID, String firmaAdi, String lokasyon, String kampanyaIcerik, String kampanyaSuresi) {
        FirmaID = firmaID;
        FirmaAdi = firmaAdi;
        Lokasyon = lokasyon;
        KampanyaIcerik = kampanyaIcerik;
        KampanyaSuresi = kampanyaSuresi;
    }

    protected magazaModel(Parcel in) {
        FirmaID = in.readString();
        FirmaAdi = in.readString();
        Lokasyon = in.readString();
        KampanyaIcerik = in.readString();
        KampanyaSuresi = in.readString();
    }

    public static final Creator<magazaModel> CREATOR = new Creator<magazaModel>() {
        @Override
        public magazaModel createFromParcel(Parcel in) {
            return new magazaModel(in);
        }

        @Override
        public magazaModel[] newArray(int size) {
            return new magazaModel[size];
        }
    };

    public String getFirmaID() {
        return FirmaID;
    }

    public String getFirmaAdi() {
        return FirmaAdi;
    }

    public String getLokasyon() {
        return Lokasyon;
    }

    public String getKampanyaIcerik() {
        return KampanyaIcerik;
    }

    public String getKampanyaSuresi() {
        return KampanyaSuresi;
    }

    public void setFirmaID(String firmaID) {
        FirmaID = firmaID;
    }

    public void setFirmaAdi(String firmaAdi) {
        FirmaAdi = firmaAdi;
    }

    public void setLokasyon(String lokasyon) {
        Lokasyon = lokasyon;
    }

    public void setKampanyaIcerik(String kampanyaIcerik) {
        KampanyaIcerik = kampanyaIcerik;
    }

    public void setKampanyaSuresi(String kampanyaSuresi) {
        KampanyaSuresi = kampanyaSuresi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FirmaID);
        dest.writeString(FirmaAdi);
        dest.writeString(Lokasyon);
        dest.writeString(KampanyaIcerik);
        dest.writeString(KampanyaSuresi);
    }
}
