/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidy.challenges;

/**
 * Interface IChallenges urcuje ake metody musi obsahovat kazdy Challenge.
 * @author Basic
 */
public interface IChallenges {
    
    /**
     * 
     * Zadanie pre hraca aby vedel co ma robit.
     */
    
    void zadanie();
    
    /**
     * 
     * Po splneni ulohy sa objavy oznamenie o ukonceni ulohy.
     */
    
    void ukonciChallenge();
    
    /**
     * 
     * @return vrati v akom stave challenge je.
     */
    
    StavChallenge getStavChallenge();
            
    void setStavChallenge(StavChallenge stavChallenge);
    
    void setNeaktivny();
}
