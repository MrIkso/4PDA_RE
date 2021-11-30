package ru.fourpda.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

class Document {
    public static int Debug_fromStream_level;
    public static int Debug_fromStream_step;
    private static final char[] BIG_UNICODE_CHARS = {0xa8, 0x80, 0x81, 0xaa, 0xbd, 0xb2, 0xaf, 0xa3, 0x8a, 0x8c,
            0x8e, 0x8d, 0x0, 0xa1, 0x8f};
    private static final char[] LOW_UNICODE_CHARS = {0xb8, 0x90, 0x83, 0xba, 0xbe, 0xb3, 0xbf, 0xbc, 0x9a, 0x9c,
            0x9e, 0x9d, 0x0, 0xa2, 0x9f};
    protected DocumentData data;

    public static class DocumentData extends Vector<Object> {
    }

    public Document() {
        data = new DocumentData();
    }

    public static String dump(Document document, int offset) {
        if (document == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder str = new StringBuilder();
        for (int i2 = 0; i2 < offset; i2++) {
            str.append("\t");
        }
        String str2 = str + "\t";
        if (document.data.size() > 0) {
            sb.append(str).append("[{").append(document.data.size()).append("}\n");
            for (int i3 = 0; i3 < document.data.size(); i3++) {
                Object obj = document.data.get(i3);
                if (obj instanceof Document) {
                    sb.append(dump((Document) obj, offset + 1));
                } else if (obj instanceof String) {
                    sb.append(str2).append("\"").append(obj).append("\"");
                } else if (obj instanceof Integer) {
                    sb.append(str2).append(obj.toString());
                } else if (obj == null) {
                    sb.append(str2).append("NULL");
                }
                sb.append("\n");
            }
            sb.append(str).append("]");
        } else {
            sb.append(str).append("[{0}]");
        }
        return sb.toString();
    }

    private static boolean fromStream(ByteArrayInputStream byteArrayInputStream, DocumentData data) {
        int read;
        int read2;
        int i;
        int i2;
        int i3 = 0;
        byteArrayInputStream.mark(0);
        int i4 = 91;
        if (91 != byteArrayInputStream.read()) {
            byteArrayInputStream.reset();
            return false;
        }
        Debug_fromStream_level++;
        Debug_fromStream_step = 0;
        while (true) {
            byteArrayInputStream.mark(0);
            while (true) {
                read = byteArrayInputStream.read();
                if (32 != read) {
                    break;
                }
                byteArrayInputStream.mark(0);
            }
            if (i4 == read) {
                Debug_fromStream_step++;
                byteArrayInputStream.reset();
                DocumentData aVar2 = new DocumentData();
                if (!fromStream(byteArrayInputStream, aVar2)) {
                    return false;
                }
                data.add(aVar2);
            } else {
                int i5 = -1;
                int i6 = 57;
                if (45 == read || (48 <= read && 57 >= read)) {
                    Debug_fromStream_step++;
                    if (45 == read) {
                        i = 0;
                    } else {
                        i = read - 48;
                        i5 = 1;
                    }
                    while (true) {
                        byteArrayInputStream.mark(0);
                        int read3 = byteArrayInputStream.read();
                        if (48 > read3 || 57 < read3) {
                            break;
                        }
                        i = (i * 10) + (read3 - 48);
                    }
                    byteArrayInputStream.reset();
                    data.add(i * i5);
                } else if (34 == read) {
                    Debug_fromStream_step++;
                    StringBuilder sb = new StringBuilder(Math.min(1024, byteArrayInputStream.available()));
                    int i7 = 0;
                    while (true) {
                        int read4 = byteArrayInputStream.read();
                        if (92 == read4) {
                            int read5 = byteArrayInputStream.read();
                            if (92 == read5) {
                                sb.append('\\');
                            } else if (116 == read5) {
                                sb.append('\t');
                                int length = 5 - ((sb.length() - i7) % 5);
                                for (int i8 = 0; i8 < length; i8++) {
                                    sb.append(' ');
                                }
                            } else if (114 == read5) {
                                sb.append('\r');
                            } else if (110 == read5) {
                                sb.append('\n');
                                i7 = sb.length();
                            } else if (102 == read5) {
                                sb.append('\f');
                            } else if (98 == read5) {
                                sb.append('\b');
                            } else if (34 == read5) {
                                sb.append('\"');
                            } else if (117 == read5) {
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < 4) {
                                    int read6 = byteArrayInputStream.read();
                                    if (48 > read6 || i6 < read6) {
                                        if (97 <= read6 && 102 >= read6) {
                                            i3 = read6 - 97;
                                        } else if (65 > read6 || 70 < read6) {
                                            i2 = 0;
                                        } else {
                                            i3 = read6 - 65;
                                        }
                                        i2 = i3 + 10;
                                    } else {
                                        i2 = read6 - 48;
                                    }
                                    i10 = (i10 << 4) | i2;
                                    i9++;
                                    i6 = 57;
                                }
                                sb.append((char) i10);
                            } else {
                                sb.append((char) read5);
                            }
                        } else if (34 == read4) {
                            data.add(sb.toString());
                            break;
                        } else if (-1 == read4) {
                            return false;
                        } else {
                            if (192 <= read4) {
                                sb.append((char) (read4 + 848));
                            } else if (128 == read4) {
                                sb.append((char) 1026);
                            } else if (129 == read4) {
                                sb.append((char) 1027);
                            } else if (130 == read4) {
                                sb.append((char) 8218);
                            } else if (131 == read4) {
                                sb.append((char) 1107);
                            } else if (132 == read4) {
                                sb.append((char) 8222);
                            } else if (133 == read4) {
                                sb.append((char) 8230);
                            } else if (134 == read4) {
                                sb.append((char) 8224);
                            } else if (135 == read4) {
                                sb.append((char) 8225);
                            } else if (136 == read4) {
                                sb.append((char) 8364);
                            } else if (137 == read4) {
                                sb.append((char) 8240);
                            } else if (138 == read4) {
                                sb.append((char) 1033);
                            } else if (139 == read4) {
                                sb.append((char) 8249);
                            } else if (140 == read4) {
                                sb.append((char) 1034);
                            } else if (141 == read4) {
                                sb.append((char) 1036);
                            } else if (142 == read4) {
                                sb.append((char) 1035);
                            } else if (143 == read4) {
                                sb.append((char) 1039);
                            } else if (144 == read4) {
                                sb.append((char) 1106);
                            } else if (145 == read4) {
                                sb.append((char) 8216);
                            } else if (146 == read4) {
                                sb.append((char) 8217);
                            } else if (147 == read4) {
                                sb.append((char) 8220);
                            } else if (148 == read4) {
                                sb.append((char) 8221);
                            } else if (149 == read4) {
                                sb.append((char) 8226);
                            } else if (150 == read4) {
                                sb.append((char) 8211);
                            } else if (151 == read4) {
                                sb.append((char) 8212);
                            } else if (152 == read4) {
                                sb.append(' ');
                            } else if (153 == read4) {
                                sb.append((char) 8482);
                            } else if (154 == read4) {
                                sb.append((char) 1113);
                            } else if (155 == read4) {
                                sb.append((char) 8250);
                            } else if (156 == read4) {
                                sb.append((char) 1114);
                            } else if (157 == read4) {
                                sb.append((char) 1116);
                            } else if (158 == read4) {
                                sb.append((char) 1115);
                            } else if (159 == read4) {
                                sb.append((char) 1119);
                            } else if (161 == read4) {
                                sb.append((char) 1038);
                            } else if (162 == read4) {
                                sb.append((char) 1118);
                            } else if (163 == read4) {
                                sb.append((char) 1032);
                            } else if (165 == read4) {
                                sb.append((char) 1168);
                            } else if (168 == read4) {
                                sb.append((char) 1025);
                            } else if (170 == read4) {
                                sb.append((char) 1028);
                            } else if (175 == read4) {
                                sb.append((char) 1031);
                            } else if (178 == read4) {
                                sb.append((char) 1030);
                            } else if (179 == read4) {
                                sb.append((char) 1110);
                            } else if (180 == read4) {
                                sb.append((char) 1169);
                            } else if (184 == read4) {
                                sb.append((char) 1105);
                            } else if (185 == read4) {
                                sb.append((char) 8470);
                            } else if (186 == read4) {
                                sb.append((char) 1108);
                            } else if (188 == read4) {
                                sb.append((char) 1112);
                            } else if (189 == read4) {
                                sb.append((char) 1029);
                            } else if (190 == read4) {
                                sb.append((char) 1109);
                            } else if (191 == read4) {
                                sb.append((char) 1111);
                            } else {
                                sb.append((char) read4);
                            }
                        }
                        i6 = 57;
                    }
                } else {
                    byteArrayInputStream.reset();
                }
            }
            do {
                read2 = byteArrayInputStream.read();
            } while (32 == read2);
            if (93 == read2) {
                Debug_fromStream_level--;
                return true;
            } else if (44 != read2) {
                return false;
            } else {
                i4 = 91;
            }
        }
    }

    private static void toStream(ByteArrayOutputStream byteArrayOutputStream, DocumentData data) {
        byteArrayOutputStream.write(91);
        for (int i = 0; i < data.size(); i++) {
            Object obj = data.get(i);
            if (i != 0) {
                byteArrayOutputStream.write(44);
            }
            if (obj != null) {
                if (obj instanceof DocumentData) {
                    toStream(byteArrayOutputStream, (DocumentData) obj);
                } else if (obj instanceof Integer) {
                    long intValue = (long) (Integer) obj;
                    if (0 > intValue) {
                        intValue = Math.abs(intValue);
                        byteArrayOutputStream.write(45);
                    }
                    if (intValue >= 1000000000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 1000000000) % 10) + 48)));
                    }
                    if (intValue >= 100000000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 100000000) % 10) + 48)));
                    }
                    if (intValue >= 10000000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 10000000) % 10) + 48)));
                    }
                    if (intValue >= 1000000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 1000000) % 10) + 48)));
                    }
                    if (intValue >= 100000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 100000) % 10) + 48)));
                    }
                    if (intValue >= 10000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 10000) % 10) + 48)));
                    }
                    if (intValue >= 1000) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 1000) % 10) + 48)));
                    }
                    if (intValue >= 100) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 100) % 10) + 48)));
                    }
                    if (intValue >= 10) {
                        byteArrayOutputStream.write((char) ((int) (((intValue / 10) % 10) + 48)));
                    }
                    byteArrayOutputStream.write((char) ((int) ((intValue % 10) + 48)));
                } else if (obj instanceof String) {
                    String str = (String) obj;
                    byteArrayOutputStream.write(34);
                    int length = str.length();
                    int i2 = 0;
                    while (i2 < length) {
                        char charAt = str.charAt(i2);
                        if (charAt >= 1040 && charAt < 1104) {
                            byteArrayOutputStream.write((char) (charAt - 848));
                        } else if (1025 <= charAt && 1040 > charAt && 1037 != charAt) {
                            byteArrayOutputStream.write(BIG_UNICODE_CHARS[charAt - 1025]);
                        } else if (1105 <= charAt && 1120 > charAt && 1117 != charAt) {
                            byteArrayOutputStream.write(LOW_UNICODE_CHARS[charAt - 1105]);
                        } else if (8211 == charAt) {
                            byteArrayOutputStream.write(150);
                        } else if (8212 == charAt) {
                            byteArrayOutputStream.write(151);
                        } else if (8216 == charAt) {
                            byteArrayOutputStream.write(145);
                        } else if (8217 == charAt) {
                            byteArrayOutputStream.write(146);
                        } else if (8218 == charAt) {
                            byteArrayOutputStream.write(130);
                        } else if (8220 == charAt) {
                            byteArrayOutputStream.write(147);
                        } else if (8221 == charAt) {
                            byteArrayOutputStream.write(148);
                        } else if (8222 == charAt) {
                            byteArrayOutputStream.write(132);
                        } else if (8224 == charAt) {
                            byteArrayOutputStream.write(134);
                        } else if (8225 == charAt) {
                            byteArrayOutputStream.write(135);
                        } else if (8226 == charAt) {
                            byteArrayOutputStream.write(149);
                        } else if (8230 == charAt) {
                            byteArrayOutputStream.write(133);
                        } else if (8240 == charAt) {
                            byteArrayOutputStream.write(137);
                        } else if (8249 == charAt) {
                            byteArrayOutputStream.write(139);
                        } else if (8250 == charAt) {
                            byteArrayOutputStream.write(155);
                        } else if (8364 == charAt) {
                            byteArrayOutputStream.write(136);
                        } else if (8470 == charAt) {
                            byteArrayOutputStream.write(185);
                        } else if (8482 == charAt) {
                            byteArrayOutputStream.write(153);
                        } else if (160 == charAt) {
                            byteArrayOutputStream.write(160);
                        } else if (164 == charAt) {
                            byteArrayOutputStream.write(164);
                        } else if (166 == charAt) {
                            byteArrayOutputStream.write(166);
                        } else if (167 == charAt) {
                            byteArrayOutputStream.write(167);
                        } else if (169 == charAt) {
                            byteArrayOutputStream.write(169);
                        } else if (171 == charAt) {
                            byteArrayOutputStream.write(171);
                        } else if (172 == charAt) {
                            byteArrayOutputStream.write(172);
                        } else if (173 == charAt) {
                            byteArrayOutputStream.write(173);
                        } else if (174 == charAt) {
                            byteArrayOutputStream.write(174);
                        } else if (176 == charAt) {
                            byteArrayOutputStream.write(176);
                        } else if (177 == charAt) {
                            byteArrayOutputStream.write(177);
                        } else if (181 == charAt) {
                            byteArrayOutputStream.write(181);
                        } else if (182 == charAt) {
                            byteArrayOutputStream.write(182);
                        } else if (183 == charAt) {
                            byteArrayOutputStream.write(183);
                        } else if (187 == charAt) {
                            byteArrayOutputStream.write(187);
                        } else if (' ' <= charAt && 128 > charAt && '\\' != charAt && '\"' != charAt) {
                            byteArrayOutputStream.write((byte) charAt);
                        } else if (charAt < 55296 || charAt > 56319) {
                            byteArrayOutputStream.write(92);
                            if ('\t' == charAt) {
                                byteArrayOutputStream.write(116);
                            } else if ('\r' == charAt) {
                                byteArrayOutputStream.write(114);
                            } else if ('\n' == charAt) {
                                byteArrayOutputStream.write(110);
                            } else if ('\f' == charAt) {
                                byteArrayOutputStream.write(102);
                            } else if ('\b' == charAt) {
                                byteArrayOutputStream.write(98);
                            } else if ('\"' == charAt) {
                                byteArrayOutputStream.write(34);
                            } else if ('\\' == charAt) {
                                byteArrayOutputStream.write(92);
                            } else {
                                byteArrayOutputStream.write(38);
                                byteArrayOutputStream.write(35);
                                String num = Integer.valueOf(charAt).toString();
                                for (int i3 = 0; i3 < num.length(); i3++) {
                                    byteArrayOutputStream.write(num.charAt(i3));
                                }
                                byteArrayOutputStream.write(59);
                            }
                        } else {
                            i2++;
                            char charAt2 = str.charAt(i2);
                            if (charAt2 < 56320 || charAt2 > 57343) {
                                byteArrayOutputStream.write(32);
                            } else {
                                byteArrayOutputStream.write(38);
                                byteArrayOutputStream.write(35);
                                String num2 = Integer.valueOf(((charAt << '\n') + charAt2) - 56613888).toString();
                                for (int i4 = 0; i4 < num2.length(); i4++) {
                                    byteArrayOutputStream.write(num2.charAt(i4));
                                }
                                byteArrayOutputStream.write(59);
                            }
                        }
                        i2++;
                    }
                    byteArrayOutputStream.write(34);
                }
            } else {
                throw new IllegalArgumentException(Integer.valueOf(i).toString());
            }
        }
        byteArrayOutputStream.write(93);
    }

    public Document append(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof String)) {
            data.add(obj);
        } else if (obj instanceof Document) {
            data.add(((Document) obj).data);
        } else {
            throw new IllegalArgumentException("Bad value");
        }
        return this;
    }

    public Document appendExtra(Object obj) {
        data.add(obj);
        return this;
    }

    public Document cloneDocument() {
        Document document = new Document();
        int size = data.size();
        for (int i = 0; i < size; i++) {
            Object obj = data.get(i);
            if ((obj instanceof Integer) || (obj instanceof String)) {
                document.data.add(obj);
            } else if (obj instanceof DocumentData) {
                document.data.add(obj);
            } else {
                throw new IllegalArgumentException("Bad value");
            }
        }
        return document;
    }

    public int count() {
        return data.size();
    }

    public Object getObject(int position) {
        if (position < data.size()) {
            return data.get(position);
        }
        throw new IndexOutOfBoundsException();
    }

    public Document prepend(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof String)) {
            data.add(0, obj);
        } else if (obj instanceof Document) {
            data.add(0, ((Document) obj).data);
        } else {
            throw new IllegalArgumentException("Bad value");
        }
        return this;
    }

    public Document remove(int position) {
        data.remove(position);
        return this;
    }

    public Document removeRange(int position, int count) {
        for (int i3 = 0; i3 < count; i3++) {
            data.remove(position);
        }
        return this;
    }

    public boolean fromStream(ByteArrayInputStream byteArrayInputStream) {
        Debug_fromStream_level = 0;
        Debug_fromStream_step = 0;
        return fromStream(byteArrayInputStream, data);
    }

    public Document getDocument(int position) {
        if (position >= data.size()) {
            return null;
        }
        Object obj = data.get(position);
        if (obj instanceof DocumentData) {
            return new Document((DocumentData) obj);
        }
        return null;
    }

    public Integer getInt(int position) {
        if (position >= data.size()) {
            return null;
        }
        Object obj = data.get(position);
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        return null;
    }

    public String getString(int position) {
        if (position >= data.size()) {
            return null;
        }
        Object obj = data.get(position);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void addInt(int position, int val) {
        if (position < data.size()) {
            data.set(position, val);
        }
    }

    public void addString(int position, String str) {
        if (str == null) {
            throw new IllegalArgumentException("Bad value");
        } else if (position < data.size()) {
            data.set(position, str);
        }
    }

    public void toStream(ByteArrayOutputStream byteArrayOutputStream) {
        toStream(byteArrayOutputStream, data);
    }

    @Override
    public String toString() {
        return dump(this, 0);
    }

    private Document(DocumentData documentData) {
        data = documentData;
    }

    public Document(Object... objects) {
        data = new DocumentData();
        for (Object obj : objects) {
            append(obj);
        }
    }
}
