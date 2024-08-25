public class Lab12Th {

    /** HOUSE ROBBERS BOTTOM UP
     *
     * After getting 15/16 on A7's autograder one too many times, you
     * have decided to turn to a life of crime.
     *
     * You have decided to do some good old fashion breaking and entering
     * to make a dishonest living and choice a nice suburban neighborhood.
     *
     * There's a catch though! The cops will catch you if you rob 2 houses
     * that are adjacent and you will go to jail if you get caught.
     *
     * You must find the most amount of money you can steal without getting
     * caught!
     *
     * TL;DR choose the correct, non-adjacent houses to rob that will
     * result in the highest possible number
     *
     * ex: [1, 0, 1, 4, 1] returns 5
     *
     * IMPORTANT: This is BOTTOM UP so NO RECURSION!
     *
     * @param houses array of payoffs of robbing houses at index i
     * @return maximum amount of payoff from robbing the houses without getting caught.
     */
    static int houseRobbersBottomUp(int[] houses) {
        int n=houses.length;
        if(n==0){
            return 0;
        }
        if(n==1){
            return houses[0];
        }
        if(n==2){
            if(houses[0]>houses[1]){
                return houses[0];
            }
            else{
                return houses[1];
            }
        }
        int[] arr=new int[n];
        arr[0]=houses[0];
        if(houses[0]>houses[1]){
            arr[1]=houses[0];
        }
        else{
            arr[1]=houses[1];
        }
        for(int i=2;i<n;i++){
            if(houses[i]+arr[i-2]>arr[i-1]){
                arr[i]=houses[i]+arr[i-2];
            }
            else{
                arr[i]=arr[i-1];
            }
        }
        return arr[n-1];
    }


    /** KNAPSACK TOP DOWN AND BOTTOM UP
     *
     * You inevitably got greedy when robbing houses and robbed 2
     * adjacent houses and got caught. (I told you not to do that!)
     * Now you must flee from the police.
     *
     * You have a problem though! You can only carry so much in
     * your trusty knapsack.
     *
     * After laying all your items out, you decided to make 2 lists
     * of how much each items weighs and how important each item is.
     *
     * You must now figure out the most of the best items you can
     * take. Better hurry, I hear sirens!
     *
     * TL;DR Return the optimal payoff without going over capacity.
     *
     * ex:
     *  capacity = 4
     *  weights = [2, 2, 3]
     *  profits = [1, 1, 3]
     *  n = 3
     *
     *  returns 3 because 3 is the highest payoff you can get
     *          without overfilling the knapsack. Taking items
     *          at index 0 and 1 result in a payoff of 2 and is
     *          suboptimal and therefore not valid.
     *
     * IMPORTANT: you must implement this TWICE. Once TOP DOWN
     * (recursion) and once BOTTOM UP (iteration)
     *
     * @param capacity most amount of weight you can carry
     * @param weights array of the weights of the ith item
     * @param profits array of the payoffs of taking the ith item.
     * @param n number of items
     * @return the highest possible payoff with the fullest knapsack.
     */

    static int knapSackTopDown(int capacity, int weights[], int profits[], int n)
    {
        int dp[][]=new int[n+1][capacity+1];
        for(int i=0;i<n+1;i++){
            for(int j = 0; j < capacity+1;j++){
                dp[i][j]=-1;
            }
        }
        return knapSackTopDown(capacity, weights, profits, n, dp);
    }
    static int knapSackTopDown(int capacity, int weights[], int profits[], int n,int[][] dp){
        if(n==0||capacity==0){
            return 0;
        }
        if(dp[n][capacity]!=-1){
            return dp[n][capacity];
        }
        if(weights[n-1]>capacity){
            return dp[n][capacity]=knapSackTopDown(capacity, weights, profits,n-1, dp);
        }
        else{
            if(profits[n-1]+knapSackTopDown(capacity-weights[n-1], weights, profits, n-1, dp)>knapSackTopDown(capacity, weights, profits, n-1, dp)){
                return dp[n][capacity]=profits[n-1]+knapSackTopDown(capacity-weights[n-1], weights, profits, n-1, dp);
            }
            else{
                return dp[n][capacity]=knapSackTopDown(capacity, weights, profits, n-1, dp);
            }
        }
    }

    static int knapSackBottomUp(int capacity, int weights[], int profits[], int n)
    {
        int[][] dp=new int[n+1][capacity+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<capacity+1;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
                else if(weights[i-1]<=j){
                    if(profits[i-1]+dp[i-1][j-weights[i-1]]>dp[i-1][j]){
                        dp[i][j]=profits[i-1]+dp[i-1][j-weights[i-1]];
                    }
                    else{
                        dp[i][j]=dp[i-1][j];
                    }
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][capacity];
    }

}
