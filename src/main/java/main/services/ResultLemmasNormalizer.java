package main.services;

import lombok.Getter;
import main.data.model.Lemma;

import java.util.HashMap;
import java.util.TreeMap;

public class ResultLemmasNormalizer {

    @Getter
    HashMap<String, Lemma> lemmaNormalizedResult;

    public ResultLemmasNormalizer(HashMap<String, Lemma> resultLemmas, TreeMap<String, Lemma> lemmasFromDb) {
        this.lemmaNormalizedResult = setNormalizedLemmas(resultLemmas, lemmasFromDb);
    }

    public static HashMap<String, Lemma> setNormalizedLemmas(HashMap<String, Lemma> resultLemmas, TreeMap<String, Lemma> lemmasFromDb){
        HashMap<String, Lemma> resultHash = new HashMap<>();
        resultLemmas.forEach((lemmaName, lemmaVal) -> {
            if(lemmasFromDb.containsKey(lemmaName)){
                lemmaVal.increaseFrequencyByVal(lemmasFromDb.get(lemmaName).getFrequency());
                lemmaVal.setId(lemmasFromDb.get(lemmaName).getId());
                resultHash.put(lemmaVal.getLemma(), lemmaVal);
            }
            resultHash.put(lemmaVal.getLemma(), lemmaVal);
        });
        return resultHash;
    }


}
