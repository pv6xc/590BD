  
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
  
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationVector;
import be.ac.ulg.montefiore.run.jahmm.OpdfMultiGaussianFactory;
import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationSequencesReader;
import be.ac.ulg.montefiore.run.jahmm.io.ObservationVectorReader;
import be.ac.ulg.montefiore.run.jahmm.learn.BaumWelchLearner;
import be.ac.ulg.montefiore.run.jahmm.learn.KMeansLearner;
  
public class TestGesture {
  
    public static void main(String[] args) throws FileFormatException,
            IOException {
  
        // Create HMM for punch gesture
  
        OpdfMultiGaussianFactory initFactoryPunch = new OpdfMultiGaussianFactory(
                3);
  
        Reader learnReaderPunch = new FileReader(
                "punchlearn.seq");
        List<List<ObservationVector>> learnSequencesPunch = ObservationSequencesReader
                .readSequences(new ObservationVectorReader(), learnReaderPunch);
        learnReaderPunch.close();
  
        KMeansLearner<ObservationVector> kMeansLearnerPunch = new KMeansLearner<ObservationVector>(
                10, initFactoryPunch, learnSequencesPunch);
        // Create an estimation of the HMM (initHmm) using one iteration of the
        // k-Means algorithm
        Hmm<ObservationVector> initHmmPunch = kMeansLearnerPunch.iterate();
  
        // Use BaumWelchLearner to create the HMM (learntHmm) from initHmm
        BaumWelchLearner baumWelchLearnerPunch = new BaumWelchLearner();
        Hmm<ObservationVector> learntHmmPunch = baumWelchLearnerPunch.learn(
                initHmmPunch, learnSequencesPunch);
  
        // Create HMM for scroll-down gesture
  
        OpdfMultiGaussianFactory initFactoryScrolldown = new OpdfMultiGaussianFactory(
                3);
  
        Reader learnReaderScrolldown = new FileReader(
                "scrolllearn.seq");
        List<List<ObservationVector>> learnSequencesScrolldown = ObservationSequencesReader
                .readSequences(new ObservationVectorReader(),
                        learnReaderScrolldown);
        learnReaderScrolldown.close();
  
        KMeansLearner<ObservationVector> kMeansLearnerScrolldown = new KMeansLearner<ObservationVector>(
                10, initFactoryScrolldown, learnSequencesScrolldown);
        // Create an estimation of the HMM (initHmm) using one iteration of the
        // k-Means algorithm
        Hmm<ObservationVector> initHmmScrolldown = kMeansLearnerScrolldown
                .iterate();
  
        // Use BaumWelchLearner to create the HMM (learntHmm) from initHmm
        BaumWelchLearner baumWelchLearnerScrolldown = new BaumWelchLearner();
        Hmm<ObservationVector> learntHmmScrolldown = baumWelchLearnerScrolldown
                .learn(initHmmScrolldown, learnSequencesScrolldown);
  
        // Create HMM for send gesture
  
        OpdfMultiGaussianFactory initFactorySend = new OpdfMultiGaussianFactory(
                3);
  
        Reader learnReaderSend = new FileReader(
                "sendlearn.seq");
        List<List<ObservationVector>> learnSequencesSend = ObservationSequencesReader
                .readSequences(new ObservationVectorReader(), learnReaderSend);
        learnReaderSend.close();
  
        KMeansLearner<ObservationVector> kMeansLearnerSend = new KMeansLearner<ObservationVector>(
                10, initFactorySend, learnSequencesSend);
        // Create an estimation of the HMM (initHmm) using one iteration of the
        // k-Means algorithm
        Hmm<ObservationVector> initHmmSend = kMeansLearnerSend.iterate();
  
        // Use BaumWelchLearner to create the HMM (learntHmm) from initHmm
        BaumWelchLearner baumWelchLearnerSend = new BaumWelchLearner();
        Hmm<ObservationVector> learntHmmSend = baumWelchLearnerSend.learn(
                initHmmSend, learnSequencesSend);
  
        Reader testReader = new FileReader(
                "scroll.seq");
        List<List<ObservationVector>> testSequences = ObservationSequencesReader
                .readSequences(new ObservationVectorReader(), testReader);
        testReader.close();
  
        short gesture; // punch = 1, scrolldown = 2, send = 3
        double punchProbability, scrolldownProbability, sendProbability;
        for (int i = 0; i <= 4; i++) {
            punchProbability = learntHmmPunch.probability(testSequences
                    .get(i));
            gesture = 1;
            scrolldownProbability = learntHmmScrolldown.probability(testSequences
                    .get(i));
            if (scrolldownProbability > punchProbability) {
                gesture = 2;
            }
            sendProbability = learntHmmSend.probability(testSequences
                    .get(i));
            if ((gesture == 1 && sendProbability > punchProbability)
                    || (gesture == 2 && sendProbability > scrolldownProbability)) {
                gesture = 3;
            }
            if (gesture == 1) {
                System.out.println("This is a punch gesture");
            } else if (gesture == 2) {
                System.out.println("This is a scroll-down gesture");
            } else if (gesture == 3) {
                System.out.println("This is a send gesture");
            }
        }
    }
  
} 