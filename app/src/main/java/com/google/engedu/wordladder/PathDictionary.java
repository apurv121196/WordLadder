/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.wordladder;

import android.util.Log;
import android.util.StringBuilderPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;

public class PathDictionary {
    private static final int MAX_WORD_LENGTH = 4;
    private static HashSet<String> words = new HashSet<>();
    private static final int MAX_PATH_SIZE = 5;

    public PathDictionary(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        Log.i("Word ladder", "Loading dict");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        Log.i("Word ladder", "Loading dict");
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() > MAX_WORD_LENGTH) {
                continue;
            }
            word = word.toLowerCase();
            words.add(word);

        }

    }

    public boolean isWord(String word) {
        return words.contains(word);
    }

    private ArrayList<String> neighbours(String word) {
        ArrayList<String> neighbours = new ArrayList<>();
        for(int i=0;i<word.length();i++){
            for(char ch='a';ch<='z';ch++){
                StringBuilder stringBuilder = new StringBuilder(word);
                stringBuilder.setCharAt(i,ch);
                if(isWord(stringBuilder.toString()))neighbours.add(stringBuilder.toString());
            }
        }
        return neighbours;
    }

    public ArrayList<String> findPath(String start, String end) {
        ArrayDeque<ArrayList<String>> arrayDeque = new ArrayDeque<>();
        ArrayList<String> poll = new ArrayList<>();
        poll.add(start);
        arrayDeque.add(poll);
        while(!arrayDeque.isEmpty()){
            ArrayList<String> removedPath = arrayDeque.remove();
            if(removedPath.size()>MAX_PATH_SIZE)break;
            String curr = removedPath.get(removedPath.size()-1);
            for(String neighbour:neighbours(curr)){
                if(Objects.equals(neighbour, end)){
                    removedPath.add(neighbour);
                    return removedPath;
                }
                else{
                    ArrayList<String> newPath = new ArrayList<>(removedPath);
                    newPath.add(neighbour);
                    arrayDeque.add(newPath);
                }
            }
        }
        return null;
    }
}
