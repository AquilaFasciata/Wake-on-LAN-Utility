package com.zach.batchwol;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PacketSender {
    // TODO Implement Broad Port Selection
    // TODO Implement Port Selection for User
    private static final int[] PORTS = {0, 7, 9};
    private static final int DEFAULT_PORT = 9;

    public static void PacketSender(String macAddress) {

        byte[] theMagic = new byte[102];
        byte[] newMacs = MacToBytes(macAddress);

        for (int i = 0; i < 6; i++)
            theMagic[i] = (byte) 0xff;

        for (int i = 6; i < theMagic.length; i += newMacs.length) {
            System.arraycopy(newMacs, 0, theMagic, i, newMacs.length);
        }

        try {
            InetAddress broadcast = InetAddress.getByName("192.168.1.255");
            System.out.println(broadcast.toString());
            DatagramPacket packet = new DatagramPacket(theMagic, theMagic.length, broadcast, DEFAULT_PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static byte[] MacToBytes(String address) {
        final char[] SEPERATORS = {':', '-'};
        int lastIndex = 0;
        byte[] result = new byte[6];
        // j is used for array changes independent of i
        int j = 0;
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == SEPERATORS[0] || address.charAt(i) == SEPERATORS[1]) {
                final int parseInt = Integer.parseInt(address.substring(lastIndex, i), 16);
                result[j] = (byte) parseInt;
                lastIndex = i + 1;
                j++;
            }
        }

        System.out.println("Mac: ");
        for (byte data : result)
            System.out.print(data);
        return result;
    }

    //    Main Method not to be used; just for testing purposes
    public static void main(String[] args) {
        PacketSender(args[0]);
    }
}