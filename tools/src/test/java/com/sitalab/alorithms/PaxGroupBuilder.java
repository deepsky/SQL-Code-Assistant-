package com.sitalab.alorithms;


import java.util.*;

public class PaxGroupBuilder {

    private Set<String> paxSet = new HashSet<String>();
    private Set<String> flightSet = new HashSet<String>();
    private List<String[]> pairs = new ArrayList<String[]>();

    private PaxGroupBuilder(){
        // TODO - implement me
    }


    public static PaxGroupBuilder create(){
        // TODO - implement me
        return new PaxGroupBuilder();
    }


    public PaxGroupBuilder addPair(String pax, String flight){
        if(pax != null && flight != null){
            paxSet.add(pax.toLowerCase());
            flightSet.add(flight.toLowerCase());

            pairs.add(new String[]{pax.toLowerCase(), flight.toLowerCase()});

        }
        return this;
    }


    public Matrix[] build(){
        // Create matrix
        List<String> paxSorted = new ArrayList(paxSet);
        Collections.sort(paxSorted);

        List<String> flightSorted = new ArrayList(flightSet);
        Collections.sort(flightSorted);
        MatrixImpl matrix = new MatrixImpl(
                paxSorted.toArray(new String[paxSet.size()]),
                flightSorted.toArray(new String[flightSet.size()])
                );

        // Populate matrix with pax-flight pairs
        for(String[] pair: pairs){
            matrix.mark(pair[0], pair[1]);
        }

        // Roll up
        processMatrix(matrix);


        // TODO - implement me
        return null;
    }


    private void processMatrix(Matrix matrix){
        final List<List<String>> winners = new ArrayList<List<String>>();
        final int[] hits = {0};
        generateCombinations(matrix, new MatrixColumnProcessor() {
            public void process(Matrix matrix, String x0, String x1) {
                List<String> localHits = new ArrayList<String>();
                for(String y: matrix.getYNames()){
                    if( matrix.isMarked(y, x0) && matrix.isMarked(y, x1)){
                        localHits.add(y + ":" + x0 + ":" + x1);
                    }
                }

                if(localHits.size()== 0){
                    // skip
                } else if(localHits.size() == hits[0]){
                    winners.add(localHits);
                } else if(localHits.size() > hits[0]){
                    winners.clear();
                    winners.add(localHits);
                    hits[0] = localHits.size();
                }
            }
        });


        int r =0;
    }


    private void generateCombinations(Matrix matrix, MatrixColumnProcessor processor){
        for(int i =0; i<matrix.getXNames().length; i++){
            for(int j = i+1; j<matrix.getXNames().length; j++){
                processor.process(matrix, matrix.getXNames()[i], matrix.getXNames()[j]);
            }
        }
    }


    interface MatrixColumnProcessor {
        void process(Matrix matrix, String x0, String x1);
    }
}
