package com.automationanywhere.botcommand.samples.misc;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.imsweb.x12.Loop;
import com.imsweb.x12.Segment;
import com.imsweb.x12.reader.X12Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class test {

    public static void main(String[] args) throws Exception {

         X12Reader reader;
        String version = "ANSI270_4010_X092";
        String path = "C:\\Users\\jamesdickson\\Documents\\EDI\\x270.txt";
        switch(version) {
            case "ANSI270_4010_X092":
                reader = new X12Reader(X12Reader.FileType.ANSI270_4010_X092, new File(path));
                //System.out.println("worked");
                break;
            case "ANSI271_4010_X092":
                reader = new X12Reader(X12Reader.FileType.ANSI271_4010_X092, new File(path));
                break;
            case "ANSI835_5010_X221":
                reader = new X12Reader(X12Reader.FileType.ANSI835_5010_X221, new File(path));
                break;
            case "ANSI835_4010_X091":
                reader = new X12Reader(X12Reader.FileType.ANSI835_4010_X091, new File(path));
                break;
            case "ANSI837_4010_X096":
                reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X096, new File(path));
                break;
            case "ANSI837_4010_X097":
                reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X097, new File(path));
                break;
            case "ANSI837_4010_X098":
                reader = new X12Reader(X12Reader.FileType.ANSI837_4010_X098, new File(path));
                break;
            case "ANSI837_5010_X222":
                reader = new X12Reader(X12Reader.FileType.ANSI837_5010_X222, new File(path));
                break;
            case "ANSI837_5010_X223":
                reader = new X12Reader(X12Reader.FileType.ANSI837_5010_X223, new File(path));
                break;
            case "ANSI277_5010_X214":
                reader = new X12Reader(X12Reader.FileType.ANSI277_5010_X214, new File(path));
                break;
            case "ANSI277_5010_X212":
                reader = new X12Reader(X12Reader.FileType.ANSI277_5010_X212, new File(path));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + version);
        }

        //X12Reader reader = new X12Reader(X12Reader.FileType.ANSI835_5010_X221, new File("C:\\ASC X12\\005010\\Technical Reports\\Type 3\\Finals\\Examples\\X835.txt"));
        List<Loop> loops = reader.getLoops();
        System.out.println(loops);

//        List<Value> intListOut = new ArrayList<>();
////        for (Iterator<Loop> nextLoop = loops.listIterator(); nextLoop.hasNext(); ) {
////            //System.out.println(nextLoop.next().toJson());
////            intListOut.add(new StringValue(String.valueOf(nextLoop.next().toJson())));
////        }
//        System.out.println(loops.size());
//        for (int i=0; i < loops.size(); i++){
//            //intListOut.add(new StringValue(reader.getLoops().get(i).toJson()));
//            //System.out.println(reader.getLoops().get(0).toJson());
//            StringValue outString = new StringValue(reader.getLoops().get(i).toJson());
//            intListOut.add(outString);
//            //System.out.println(outString);
//        }
//        System.out.println(intListOut);

//        Loop loop = reader.getLoops().get(0);
//        String jsonLoops = loop.toJson();
//        System.out.println("JSON: " + jsonLoops);

//        String data = loop.getLoop("2100C")
//                .getSegment("NM1")
//                .getElementValue("NM101");
//        System.out.println(data);
//
//        Loop loop2100 = loop.getLoop("2100C");
//        System.out.println("2100C Loop: " + loop2100);
//
//        List<Loop> subloops = loop.findLoop("2100A");
//        System.out.println(subloops.get(0));
//
//        Segment isa = loop.getSegment("ISA");
//        System.out.println("ISA Segment: " + isa);
//
//
//        Loop l2100C = loop.getLoop("2100C");
//        List<Segment> segments = l2100C.findSegment("NM1");
//        System.out.println(segments);


    }
}



