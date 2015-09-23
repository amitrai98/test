package com.example.amitrai.internettest.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amitrai on 23/9/15.
 */
public class UsageModal implements Parcelable{

    String account_no;
    int usage, coast;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    protected UsageModal(Parcel in) {
        account_no = in.readString();
        usage = in.readInt();
        coast = in.readInt();
    }

    public UsageModal(String account_no, int usage, int coast){
        this.account_no = account_no;
        this.usage = usage;
        this.coast = coast;
    }

    public static final Creator<UsageModal> CREATOR = new Creator<UsageModal>() {
        @Override
        public UsageModal createFromParcel(Parcel in) {
            return new UsageModal(in);
        }

        @Override
        public UsageModal[] newArray(int size) {
            return new UsageModal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(account_no);
        dest.writeInt(usage);
        dest.writeInt(coast);
    }
}
