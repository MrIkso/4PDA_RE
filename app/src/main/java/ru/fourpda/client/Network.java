package ru.fourpda.client;

import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public abstract class Network {
    private volatile boolean isConnected2;
    private volatile boolean isConnected;
    private volatile boolean altPath;
    private volatile Thread connectionThread;
    private volatile Socket tcpSocket;
    private volatile OutputStream outputStream;
    private long connStart;
    private long connTime;
    private long f3182j;
    private final Object locker = new Object();
    private final Runnable connectionRunnable = new Runnable(){
        @Override
        public void run() {
            DataInputStream dataInputStream;
            DataInputStream tcpSocketStream;
            DataInputStream webSocketStream = null;
            DataInputStream resultStream = null;
            ByteArrayInputStream byteArrayInputStream;
            byte[] contentBytes;
            byte[] headerBytes = new byte[10];
            byte[] receivedBytes = new byte[65536];
            Inflater decompressor = new Inflater(true);
            boolean z;
            boolean z2 = false;
            while (true) {
                Thread.interrupted();
                if (z2 || !Network.this.isConnected2) {
                    Log.d("NETWORK", "close connection");
                    boolean z3 = Network.this.isConnected;
                    Network.this.isConnected = false;
                    if (z3) {
                        decompressor.reset();
                        Network.this.mo91E();
                    }
                    dataInputStream = null;
                    Network.this.outputStream = null;
                    if (Network.this.tcpSocket != null) {
                        try {
                            Network.this.tcpSocket.close();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    Network.this.tcpSocket = null;
                } else {
                    dataInputStream = resultStream;
                }
                while (!Network.this.isConnected2) {
                    try {
                        synchronized (this) {
                            Log.d("NETWORK", "wait");
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                boolean z4 = false;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (!Network.this.isConnected && elapsedRealtime - Network.this.f3182j < 1000) {
                    try {
                        Log.d("NETWORK", "Thread.sleep" + elapsedRealtime);
                        Thread.sleep((Network.this.f3182j + 1000) - elapsedRealtime);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                Network.this.f3182j = elapsedRealtime;
                if (!Network.this.isConnected2 || Network.this.tcpSocket != null) {
                    resultStream = dataInputStream;
                } else

                // create connection to app.4pda.to
                {
                    Network.this.isConnected = false;
                    Network.this.altPath = false;
                    Network.this.connectedServer();
                    try {
                        Network.this.tcpSocket = new Socket();
                        Network.this.tcpSocket.setKeepAlive(true);
                        if (262144 < Network.this.tcpSocket.getSendBufferSize()) {
                            Network.this.tcpSocket.setSendBufferSize(262144);
                        }
                        Network.this.tcpSocket.connect(new InetSocketAddress("app.4pda.to", 993), 10000);
                        Network.this.outputStream = Network.this.tcpSocket.getOutputStream();
                        tcpSocketStream = new DataInputStream(Network.this.tcpSocket.getInputStream());
                        Log.d("NETWORK", "getting input stream");
                    } catch (Throwable th2) {
                        try {
                            Network.this.tcpSocket.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                        Network.this.tcpSocket = null;
                        tcpSocketStream = null;
                    }
                   if (Network.this.isConnected2 && Network.this.tcpSocket == null) {
                        Log.d("NETWORK", "try another connection!");
                        Network.this.altPath = true;
                        try {
                            Network.this.tcpSocket = new Socket();
                            Network.this.tcpSocket.setKeepAlive(true);
                            if (262144 < Network.this.tcpSocket.getSendBufferSize()) {
                                Network.this.tcpSocket.setSendBufferSize(262144);
                            }
                            Network.this.tcpSocket.connect(new InetSocketAddress("app.4pda.ru", 80), 10000);
                            Network.this.tcpSocket.setSoTimeout(5000);
                            Network.this.outputStream = Network.this.tcpSocket.getOutputStream();
                            webSocketStream = new DataInputStream(Network.this.tcpSocket.getInputStream());

                            byte[] bArr4 = new byte[16];
                            new Random().nextBytes(bArr4);
                            Network.this.outputStream.write(("GET /ws/ HTTP/1.1\r\nHost: app.4pda.ru\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Key: " + Base64.encodeToString(bArr4, 2) + "\r\nSec-WebSocket-Protocol: app\r\nSec-WebSocket-Version: 13\r\n\r\n").getBytes());
                            byte[] bArr5 = new byte[1024];
                            int i = 0;
                            while (true) {
                                int read = webSocketStream.read(bArr5, i, bArr5.length - i);
                                if (read <= 0 || (i = i + read) > 1000 || (i >= 10 && bArr5[i - 1] == 10 && bArr5[i - 2] == 13 && bArr5[i - 3] == 10 && bArr5[i - 4] == 13)) {
                                    break;
                                }
                            }
                            if (!new String(bArr5, 0, i).contains("101 Switching Protocols")) {
                                throw new IOException("Bad WS reply");
                            }
                            tcpSocketStream = webSocketStream;
                        } catch (Throwable th5) {
                            tcpSocketStream = webSocketStream;
                            if (Network.this.tcpSocket != null) {
                                try {
                                    Network.this.tcpSocket.close();
                                } catch (Throwable th6) {
                                    th6.printStackTrace();
                                }
                                Network.this.tcpSocket = null;
                            }
                            if (Network.this.tcpSocket != null) {
                            }
                            resultStream = tcpSocketStream;
                            if (!Network.this.isConnected2) {
                            }
                            z2 = z4;
                            th5.printStackTrace();
                        }
                    }
                    if (Network.this.tcpSocket != null) {
                        try {
                            Network.this.tcpSocket.setSoTimeout(90000);
                        } catch (SocketException e3) {
                            e3.printStackTrace();
                        }
                        Log.d("NETWORK", "connected!");
                        Network.this.isConnected = true;
                        Network.this.connTime = SystemClock.elapsedRealtime();
                        Network.this.connectedForum();
                    }
                    resultStream = tcpSocketStream;
                }
                // parsing socket
                if (true) {
                    Log.d("NETWORK", "socket start parse");
                    if (Network.this.tcpSocket == null) {
                        Log.d("NETWORK", "tcp socket null");
                        z2 = false;
                    } else {
                        try {
                            resultStream.readFully(headerBytes, 0, 2);
                            int cmd = headerBytes[0] & 255;
                            int dataSize = headerBytes[1] & 255;
                            if (126 == dataSize) {
                                resultStream.readFully(headerBytes, 2, 2);
                                dataSize = ((headerBytes[2] & 255) << 8)
                                        | (headerBytes[3] & 255);
                            } else if (127 == dataSize) {
                                resultStream.readFully(headerBytes, 2, 8);
                                dataSize = ((headerBytes[6] & 255) << 24)
                                        | ((headerBytes[7] & 255) << 16)
                                        | ((headerBytes[8] & 255) << 8)
                                        | (headerBytes[9] & 255);
                            } else if (dataSize < 0) {
                                dataSize = 0;
                                cmd = 0;
                            }
                            if (dataSize <= receivedBytes.length) {
                                contentBytes = receivedBytes;
                            } else {
                                try {
                                    contentBytes = new byte[dataSize];
                                } catch (OutOfMemoryError e4) {
                                    e4.printStackTrace();
                                  /*  C0636ACRA.getErrorReporter().putCustomData("header", new String(Base64.encode(headerBytes, 0, 10, 2)));
                                    C0636ACRA.getErrorReporter().handleSilentException(e4);
                                    C0636ACRA.getErrorReporter().removeCustomData("header");*/
                                    throw new IOException();
                                }
                            }
                            Log.d("NETWORK", String.format("header %s", new String(Base64.encode(headerBytes, 0, 10, 2))));
                            resultStream.readFully(contentBytes, 0, dataSize);
                            if (8 == (cmd & 15)) {
                                Network.this.sendDocument(8, true, false, contentBytes, dataSize);
                                Network.this.mo86J();
                            } else if (9 == (cmd & 15)) {
                                Network.this.sendDocument(10, true, false, contentBytes, dataSize);
                                Network.this.mo88H();
                            } else if (10 == (cmd & 15)) {
                                Network.this.mo87I();
                            } else if (1 == (cmd & 15) || 2 == (cmd & 15)) {
                                if ((cmd & 64) != 0) {
                                    try {
                                        // uncompress data
                                        byte[] compressedBytes = new byte[(contentBytes[0] & 255)
                                                | ((contentBytes[1] & 255) << 8)
                                                | ((contentBytes[2] & 255) << 16)
                                                | ((contentBytes[3] & 255) << 24)];
                                        decompressor.setInput(contentBytes, 4, dataSize - 4);
                                        decompressor.inflate(compressedBytes);
                                        decompressor.setInput(new byte[]{0, 0, -1, -1});
                                        decompressor.inflate(contentBytes);
                                        byteArrayInputStream = new ByteArrayInputStream(compressedBytes);
                                    } catch (OutOfMemoryError e5) {
                                      /*  C0636ACRA.getErrorReporter().putCustomData("header", new String(Base64.encode(headerBytes, 0, 10, 2)));
                                        C0636ACRA.getErrorReporter().putCustomData("data", new String(Base64.encode(contentBytes, 0, dataSize, 2)));
                                        C0636ACRA.getErrorReporter().handleSilentException(e5);
                                        C0636ACRA.getErrorReporter().removeCustomData("header");
                                        C0636ACRA.getErrorReporter().removeCustomData("data");*/
                                        throw new IOException();
                                    }
                                } else {
                                    // normal data
                                    byteArrayInputStream = new ByteArrayInputStream(contentBytes, 0, dataSize);
                                }
                                Log.d("NETWORK","start parsing bytearray");
                                Document document = new Document();
                                Exception e6 = null;
                                try {
                                    z = document.fromStream(byteArrayInputStream);
                                    Log.d("NETWORK","parse: "+ z);
                                } catch (Exception e7) {
                                    e6 = e7;
                                    z = false;
                                }
                                if (!z) {
                                    Network.this.parseDocumentError();
                                    throw new Exception("Document Parse Error Occurred: level=" + Document.Debug_fromStream_level + " step=" + Document.Debug_fromStream_step + " " + byteArrayInputStream.toString(), e6);
                                }
                                Network.this.HandleDocument(document);
                            } else {
                                throw new IOException();
                            }
                            if (!Network.this.isConnected2) {
                                throw new IOException();
                            }
                            z2 = false;
                        } catch (Throwable th7) {
                            z4 = true;
                            if (!(th7 instanceof IOException)) {
                                th7.printStackTrace();
                               /* C0636ACRA.getErrorReporter().putCustomData("altpath", Network.this.f3175c ? "true" : "false");
                                C0636ACRA.getErrorReporter().putCustomData("conntime", Long.valueOf(SystemClock.elapsedRealtime() - Network.this.f3181i).toString());
                                C0636ACRA.getErrorReporter().handleSilentException(th7);
                                C0636ACRA.getErrorReporter().removeCustomData("altpath");
                                C0636ACRA.getErrorReporter().removeCustomData("conntime");*/
                            }
                        }
                    }
                }
               z2 = z4;
            }
        }
    };

    @SuppressLint("StaticFieldLeak")
    private class SendDocumentTask extends AsyncTask<Void, Void, Void> {
        private SendDocumentTask() {
        }

        public Void doInBackground(Void... voidArr) {
            sendDocument(9, true, false, null, 0);
            return null;
        }

        SendDocumentTask(Network yVar) {
            this();
        }
    }

    private class SendDocumentRequestTask extends AsyncTask<DocumentManager.IGenerateRequest, Void, Void> {
        private SendDocumentRequestTask() {
        }

        public Void doInBackground(DocumentManager.IGenerateRequest... requests) {
            for (DocumentManager.IGenerateRequest request : requests) {
                sendDocumentRequest(request);
            }
            return null;
        }

        SendDocumentRequestTask(Network yVar) {
            this();
        }
    }

    public boolean sendDocument(int cmd, boolean isFile, boolean isCompress, byte[] data, int size) {
        Log.d("NETWORK", "sending document");
        int dataSize;
        synchronized (this.locker) {
            if (!isConnected()) {
                return false;
            }
            int blockLen = 4;
            if (isCompress) {
                Deflater compressor = new Deflater(6, true);
                compressor.setInput(data, 0, size);
                compressor.finish();
                data = new byte[size + 100];
                dataSize = compressor.deflate(data) + 4;
            } else {
                dataSize = size;
            }
            byte[] headerBytes = new byte[14];
            headerBytes[0] = (byte) ((cmd & 15) | (isFile ? 128 : 0) | (isCompress ? 64 : 0));
            if (125 >= dataSize) {
                headerBytes[1] = (byte) dataSize;
                blockLen = 2;
            } else if (65535 >= dataSize) {
                headerBytes[1] = 126;
                headerBytes[2] = (byte) ((dataSize >> 8) & 255);
                headerBytes[3] = (byte) ((dataSize >> 0) & 255);
            } else {
                headerBytes[1] = Byte.MAX_VALUE;
                headerBytes[2] = 0;
                headerBytes[3] = 0;
                headerBytes[4] = 0;
                headerBytes[5] = 0;
                headerBytes[6] = (byte) ((dataSize >> 24) & 255);
                headerBytes[7] = (byte) ((dataSize >> 16) & 255);
                headerBytes[8] = (byte) ((dataSize >> 8) & 255);
                blockLen = 10;
                headerBytes[9] = (byte) ((dataSize >> 0) & 255);
            }
            if (isCompress) {
                dataSize -= 4;
                int i5 = blockLen + 1;
                headerBytes[blockLen] = (byte) ((size >> 0) & 255);
                int i6 = i5 + 1;
                headerBytes[i5] = (byte) ((size >> 8) & 255);
                int i7 = i6 + 1;
                headerBytes[i6] = (byte) ((size >> 16) & 255);
                blockLen = i7 + 1;
                headerBytes[i7] = (byte) ((size >> 24) & 255);
            }
            try {
                this.outputStream.write(headerBytes, 0, blockLen);
                if (dataSize > 0) {
                    this.outputStream.write(data, 0, dataSize);
                }
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    @SuppressLint("WrongConstant")
    public boolean sendDocumentRequest(DocumentManager.IGenerateRequest request) {
        PowerManager.WakeLock wakeLock;
        synchronized (this.locker) {
            if (!isConnected()) {
                return false;
            }
            Document requestDocument = request.getDocument();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            requestDocument.toStream(byteArrayOutputStream);
            if (!sendDocument(1, request.isFileSize() == 0, true, byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size())) {
                return false;
            }
            int m = request.isFileSize();
            if (m <= 0) {
                return true;
            }
            WifiManager.WifiLock wifiLock = null;
            try {
                wakeLock = ((PowerManager) DocumentManager.documentManager.context.getSystemService("power")).newWakeLock(1, "fourpda:UploadCPULock");
                try {
                    wakeLock.acquire();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                wakeLock = null;
            }
            try {
                wifiLock = ((WifiManager) DocumentManager.documentManager.context.getApplicationContext().getSystemService("wifi")).createWifiLock(1, "UploadWifiLock");
                wifiLock.acquire();
            } catch (Exception unused3) {
            }
            int len = Math.min(131072, Math.max(4096, m / 4));
            byte[] fileBytes = new byte[len];
            boolean z = true;
            while (request.isFileSize() > 0) {
                z = sendDocument(0, request.isFileSize() == 0, true, fileBytes, request.onUploadFile(fileBytes, len));
                if (!z) {
                    break;
                }
                this.f3182j = SystemClock.elapsedRealtime();
            }
            if (wifiLock != null) {
                try {
                    wifiLock.release();
                } catch (Exception unused4) {
                    unused4.printStackTrace();
                }
            }
            if (wakeLock != null) {
                try {
                    wakeLock.release();
                } catch (Exception unused5) {
                    unused5.printStackTrace();
                }
            }
            return z;
        }
    }

    public boolean isAltPath() {
        return this.altPath;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    protected abstract void connectedServer();

    protected abstract void connectedForum();

    protected abstract void mo91E();

    protected abstract void HandleDocument(Document document);

    protected abstract void parseDocumentError();

    protected abstract void mo88H();

    protected abstract void mo87I();

    protected abstract void mo86J();

    public long getStartNETtime() {
        if (this.isConnected2) {
            return SystemClock.elapsedRealtime() - this.connStart;
        }
        return 0;
    }

    public long getConnectTime() {
        if (this.isConnected) {
            return SystemClock.elapsedRealtime() - this.connTime;
        }
        return 0;
    }

    public boolean isDataSent() {
        if (!isConnected()) {
            return false;
        }
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            new SendDocumentTask(this).execute();
            return true;
        }
        DocumentManager.documentManager.handler.post(new Runnable() {
            @Override
            public void run() {
                new SendDocumentTask(Network.this).execute();
            }
        });
        return true;
    }

    public synchronized void closeNet() {
        if (this.isConnected2) {
            Socket socket = this.tcpSocket;
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception unused) {
                }
            }
            if (this.connectionThread != null) {
                this.connectionThread.interrupt();
            }
        }
    }

    public boolean sendRequest(DocumentManager.IGenerateRequest jVar) {
        if (!isConnected()) {
            return false;
        }
        if (SystemClock.elapsedRealtime() - this.f3182j > 100000) {
            closeNet();
            return false;
        }
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            new SendDocumentRequestTask(this).execute(jVar);
        } else {
            DocumentManager.documentManager.handler.post(new Runnable() {
                @Override
                public void run() {
                    new SendDocumentRequestTask(Network.this).execute(jVar);
                }
            });
        }
        return true;
    }

    public long m63w() {
        if (this.isConnected) {
            return SystemClock.elapsedRealtime() - this.f3182j;
        }
        return 0;
    }

    public synchronized boolean initConnection() {
        if (this.isConnected2) {
            return false;
        }
        this.isConnected2 = true;
        if (this.connectionThread == null) {
            this.connectionThread = new Thread(this.connectionRunnable);
            try {
                this.connectionThread.start();
            } catch (OutOfMemoryError unused) {
                this.connectionThread = null;
            }
        } else {
            closeNet();
        }
        if (this.connectionThread == null) {
            this.isConnected2 = false;
        }
        if (this.isConnected2) {
            this.connStart = SystemClock.elapsedRealtime();
        }
        return this.isConnected2;
    }

    public synchronized boolean closeNet2() {
        if (!this.isConnected2) {
            return false;
        }
        this.isConnected2 = false;
        Socket socket = this.tcpSocket;
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception unused) {
            }
        }
        if (this.connectionThread != null) {
            this.connectionThread.interrupt();
        }
        return true;
    }

    public boolean isConnected2() {
        return this.isConnected2;
    }
}
