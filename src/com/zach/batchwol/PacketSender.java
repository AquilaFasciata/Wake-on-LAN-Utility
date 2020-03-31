package com.zach.batchwol;

import java.net.InetAddress;

public class PacketSender {
    private final short PORT = 9;

    public static void PacketSender(String macAddress) {

        byte[] theMagic = new byte[102];
        byte[] newMacs = MacToBytes(macAddress);

        for (int i = 0; i < 6; i++)
            theMagic[i] = (byte) 0xff;

        for (int i = 6; i < theMagic.length; i += macAddress.length()) {
            System.arraycopy(newMacs, 0, theMagic, i, newMacs.length);
        }

        try {
            InetAddress broadcast = InetAddress.getByName("255.255.255.255");
            System.out.println(broadcast.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        for (byte newByte: theMagic)
            System.out.print(newByte);
    }

    private static byte[] MacToBytes(String address) {
        final char[] SEPERATORS = {':','-'};
        int lastIndex = 0;
        String finalAddress = "";
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == SEPERATORS[0] || address.charAt(i) == SEPERATORS[1]) {
                finalAddress += address.substring(lastIndex, i);
                lastIndex = i+1;
            }
        }


        return finalAddress.getBytes();
    }

    public static void main(String[] args) {
        PacketSender(args[0]);
    }
}