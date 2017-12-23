package com.sara.tfidf;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TfIdfClass {

	Map<String, Map<String, Integer>> fileTermFreq = new HashMap<String, Map<String, Integer>>();
	Set<String> distinctTerms = new TreeSet<String>();

	public double getTF(String term, String file) {
		Map<String, Integer> termFreq = fileTermFreq.get(file);
		int termCount = termFreq.size();
		Integer freq = termFreq.get(term);
		if (freq == null)
			freq = 0;
		return ((double) freq / (double) termCount);
	}

	public double getIDF(String term) {
		int N = fileTermFreq.size();
		int count = 0;
		for (String fileName : fileTermFreq.keySet()) {
			if (fileTermFreq.get(fileName).get(term) != null)
				++count;
		}
		return Math.log(((double) N / (double) count));

	}

	public void addExtractedTermFromFile(File source) {
		try {
			@SuppressWarnings("resource")
			Scanner in = new Scanner(source).useDelimiter("\\W+");
			String name = source.getName();
			if (!fileTermFreq.containsKey(name)) {
				fileTermFreq.put(name, new HashMap<String, Integer>());
			} else {
				System.out.println("file " + name + " already loaded");
				return;
			}

			Map<String, Integer> termFreq = fileTermFreq.get(name);

			while (in.hasNext()) {
				String nextTerm = in.next();

				nextTerm = nextTerm.toLowerCase();
				distinctTerms.add(nextTerm);
				if (termFreq.get(nextTerm) != null) {
					// already inserted in the map
					int oldVal = termFreq.get(nextTerm);
					termFreq.put(nextTerm, oldVal + 1);

				} else {
					termFreq.put(nextTerm, 1);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addExtractedTermFromAllFiles(String folderPath) {
		File[] files = new File(folderPath).listFiles();

		for (File source : files) {
			addExtractedTermFromFile(source);
		}
	}

	public int calEspace(int sizeterme) {

		int size = 20 - sizeterme;
		return size;
	}

	public void showTermMatrix() {
		Set<String> filesNames = fileTermFreq.keySet();
		System.out.print("        \t\t\t");
		for (String fileName : filesNames) {
			System.out.print(fileName + "         \t\t\t       ");
		}

		System.out.println("\n");

		for (String term : distinctTerms) {
			int termsize = term.length();
			int espace = calEspace(termsize);
			String esp = getNbrSpace(espace);
			System.out.print(term + esp);
			for (String fileName : filesNames) {
				Map<String, Integer> termFreq = fileTermFreq.get(fileName);
				Integer integer = termFreq.get(term);
				if (integer == null) {
					integer = 0;
				}
				double tf = getTF(term, fileName);
				double idf = getIDF(term);
				double tfIdf = tf * idf;
				DecimalFormat decimalFormat = new DecimalFormat("#.####");
				// decimalFormat.format(tfIdf);
				System.out.print("\t\t" + decimalFormat.format(tfIdf) + "        \t\t\t");
//				System.out.print("\t\t" + integer + "        \t\t\t");
			}
			System.out.println();
		}
	}

	private String getNbrSpace(int espace) {
		String result = "";
		for (int i = 0; i != espace; ++i)
			result += " ";
		return result;
	}

}
