package com.example.gowthamkrishna.tourex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Processed extends AppCompatActivity {

    private List<CompositionList> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CompositionListAdapter mAdapter;

    List<List<serviceStore>> serviceList = new ArrayList<List<serviceStore>>();
    List<serviceStore> selectedComposition = new ArrayList<serviceStore>();
    ArrayList<String> services = new ArrayList<String>();
    ArrayList<Integer> qos = new ArrayList<Integer>();
    HashMap<Integer,ArrayList<serviceStore>> compositionMapping= new HashMap<Integer,ArrayList<serviceStore>>();
    ArrayList<serviceStore> tempComposition = new ArrayList<serviceStore>();
    List<serviceStore> selectedServicesForNextPage = new ArrayList<serviceStore>();
    int count;

    Button button;
    EditText input;
    String input_composition;
    int input_composition_val;

    int service_no = 0, serviceCount=0;
    int i,j,k;
    int numOfServices=0;
    int low_count[] = new int[8];
    int medium_count[] = new int[8];
    int high_count[] = new int[8];
    serviceStore[][] service;
    serviceStore[][] feedbackCopyOfService;

    int user_val[]=new int[6];
    double decision_matrix[][][]=new double[8][6][10];
    double standardised_decision_matrix_factor[][]=new double[8][6];
    double standardised_decision_matrix[][][]=new double[8][6][10];
    double weighted_std_matrix[][][]=new double[8][6][10];
    double ideal_soln[][]=new double[8][6];
    double n_ideal_soln[][]=new double[8][6];
    double ideal_soln_matrix[][][]=new double[8][6][10];
    double n_ideal_soln_matrix[][][]=new double[8][6][10];
    double s_one_star[][]=new double[8][10];
    double s_one_dash[][]=new double[8][10];
    double s_one_star_plus_dash[][]=new double[8][10];
    double resultant_solution[][]=new double[8][10];

    String composition_string[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processed);

        //Fetching data from previous activity
        Intent intent=getIntent();
        services = intent.getStringArrayListExtra("services");
        qos = intent.getIntegerArrayListExtra("qos");
        serviceList = (ArrayList<List<serviceStore>>)intent.getSerializableExtra("serviceList");

        //Fetching the number of services
        numOfServices = services.size();
        button = findViewById(R.id.button3);
        input = findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_composition = input.getText().toString();
                if(validate()){
                    getSelectedComposition();
                    Intent newIntent = new Intent(Processed.this,selectedComposition.class);
                    newIntent.putExtra("SelectedComposition" , (ArrayList<serviceStore>) selectedServicesForNextPage);
                    newIntent.putExtra("services", services);
                    startActivity(newIntent);

                }else{
                    Toast.makeText(getApplicationContext(),"Please Check Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Printing the selected services
        System.out.println("Selected Services:");
        for(String tempservices: services)
            System.out.println(tempservices);
        System.out.println("--------------------");

        //Initialising High,Low and Medium count values to 0
        for(i=0;i<numOfServices;i++)
        {
            high_count[i]=0;
            medium_count[i]=0;
            low_count[i]=0;
        }

        //Initialing the serviceStore class
        service=new serviceStore[numOfServices][10];
        feedbackCopyOfService=new serviceStore[numOfServices][10];
        for(i=0;i<numOfServices;i++)
            for(j=0;j<10;j++)
                service[i][j] = new serviceStore();

        i=0;
        for(int val : qos)
            user_val[i++] = val;

        //Printing the user Input - QoS parameters
        System.out.print("\n----------------------------");
        System.out.print("\nUser Input - QoS Parameters:");
        System.out.print("\n----------------------------\n");
        for(i=0;i<6;i++)
            System.out.println(user_val[i]);

        //System.out.println(serviceList.get(0).get(0).getTitle());

        service_no = 0;
        do{
            serviceCount=0;
            for(serviceStore temp : serviceList.get(Integer.parseInt(services.get(service_no)))){
                System.out.println("title-------" + temp.getTitle());
                service[service_no][serviceCount] = temp;
                serviceCount++;
            }

            //Printing the fetched services
            System.out.print("\n-----------");
            System.out.print("\nInput Data");
            System.out.print("\n-----------");
            System.out.print("\nService\tTitle\tAvail\tCost\tFreq.\tReput.\tResp.\tSuccess\n");
            for(i=0; i<10;i++)
                service[service_no][i].printServiceQos();

            //Fuzzy Algorithm
            //Fuzzy Classification on the services based on reputation value
            for(i=0;i<serviceCount;i++)
            {

                //Low Reputation
                if(service[service_no][i].reputation>=0 && service[service_no][i].reputation<4){
                    service[service_no][i].fuzzy_tag=0;
                    low_count[service_no]++;
                }

                //Medium Reputation
                if(service[service_no][i].reputation>=4 && service[service_no][i].reputation<7){
                    service[service_no][i].fuzzy_tag=1;
                    medium_count[service_no]++;
                }

                //High Reputation
                if(service[service_no][i].reputation>=7 && service[service_no][i].reputation<=10){
                    service[service_no][i].fuzzy_tag=2;
                    high_count[service_no]++;
                }
            }

            //Output after first fuzzy classification
            System.out.print("\n----------------------------------------------------");
            System.out.print("\nAfter first fuzzy Classification based on Reputation");
            System.out.print("\n----------------------------------------------------");
            System.out.print("\nService\tReput.\tTag\n");
            for(i=0;i<10;i++)
                service[service_no][i].printFuzzyTag();

            //Sorting After first fuzzy Classification
            for(i=0;i<serviceCount;i++){
                for(j=i;j<serviceCount;j++){
                    if(service[service_no][i].reputation<service[service_no][j].reputation){
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][i];
                        service[service_no][i]=service[service_no][j];
                        service[service_no][j]=temp;
                    }
                }
            }

            //Output after sorting the results of first fuzzy classification
            System.out.print("\n-------------------------------------------------------");
            System.out.print("\nAfter sorting the results of first fuzzy Classification");
            System.out.print("\n-------------------------------------------------------");
            System.out.print("\nService\tReput.\tTag\n");
            for(i=0;i<10;i++)
                service[service_no][i].printFuzzyTag();

            //Sorting agiain, the results of the sorted Fuzzy Classification - based on success rate
            for(i=0;i<=high_count[service_no];i++){
                for(j=i;j<high_count[service_no];j++){
                    if(service[service_no][i].success_rate<service[service_no][j].success_rate){
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][i];
                        service[service_no][i]=service[service_no][j];
                        service[service_no][j]=temp;
                    }
                }
            }

            for(j=high_count[service_no];j<high_count[service_no]+medium_count[service_no];j++){
                for(k=j;k<high_count[service_no]+medium_count[service_no];k++){
                    if(service[service_no][j].success_rate<service[service_no][k].success_rate){
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][j];
                        service[service_no][j]=service[service_no][k];
                        service[service_no][k]=temp;
                    }
                }
            }

            for(k=high_count[service_no]+medium_count[service_no];k<high_count[service_no]+medium_count[service_no]+low_count[service_no];k++){
                for(i=k;i<high_count[service_no]+medium_count[service_no]+low_count[service_no];i++){
                    if(service[service_no][k].success_rate<service[service_no][i].success_rate){
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][k];
                        service[service_no][k]=service[service_no][i];
                        service[service_no][i]=temp;
                    }
                }
            }


            //Output after sorting the results of second fuzzy classification
            System.out.print("\n--------------------------------------------------------");
            System.out.print("\nAfter sorting the results of second fuzzy Classification");
            System.out.print("\n--------------------------------------------------------");
            System.out.print("\nService\tReput.\tTag\n");
            for(i=0;i<10;i++)
                service[service_no][i].printFuzzyTag();

            //Complete Output with Qos parameters after sorting the results of second fuzzy classification
            System.out.print("\n------------------------------------------------------------------");
            System.out.print("\nFinal result of web services after fuzzy classififcation algorithm");
            System.out.print("\n------------------------------------------------------------------");
            System.out.print("\nService\tAvail\tCost\tFreq.\tReput.\tResp.\tSuccess\tFuzzy_Tag\n");
            for(i=0; i<10;i++)
                service[service_no][i].printServiceQosWithFuzzyTag();

            //Topsis Algorithm
            int row=0,column=0;
            for(i=0;i<serviceCount;i++){
                decision_matrix[service_no][row++][column]=service[service_no][i].availability;
                decision_matrix[service_no][row++][column]=service[service_no][i].cost;
                decision_matrix[service_no][row++][column]=service[service_no][i].frequency;
                decision_matrix[service_no][row++][column]=service[service_no][i].reputation;
                decision_matrix[service_no][row++][column]=service[service_no][i].response;
                decision_matrix[service_no][row++][column]=service[service_no][i].success_rate;
                ++column;
                row=0;
            }

            //The decision matrix for TOPSIS implementation
            System.out.print("--------------------\n");
            System.out.print("The Decision matrix:\n");
            System.out.print("--------------------\n");
            for(i=0;i<6;i++)
            {
                for(j=0;j<10;j++)
                    System.out.print(decision_matrix[service_no][i][j] + "\t");
                System.out.println();
            }

            //Standardising factor for the decision matrix
            double sum=0,avg=0;
            for(i=0;i<6;i++){
                sum=0;
                for(j=0;j<10;j++)
                    sum=sum+(decision_matrix[service_no][i][j]+decision_matrix[service_no][i][j]);
                avg=Math.sqrt(sum);
                avg=avg*100.0;
                avg=Math.round(avg);
                avg=avg/100.0;
                standardised_decision_matrix_factor[service_no][i]=avg;
            }

            System.out.println("----------------");
            System.out.println("Decision Factor:");
            System.out.println("----------------");
            for(i=0;i<6;i++)
                System.out.println(standardised_decision_matrix_factor[service_no][i]);

            //Forming the standardised decision matrix from decision matrix and the decision factor
            for(i=0;i<6;i++){
                for(j=0;j<10;j++){
                    double temp;
                    temp=decision_matrix[service_no][i][j]/standardised_decision_matrix_factor[service_no][i];
                    temp=temp*100.0;
                    temp=Math.round(temp);
                    temp=temp/100.0;
                    standardised_decision_matrix[service_no][i][j]=temp;
                }
            }

            //Printing the Standardised decision matrix
            System.out.println("-----------------------------");
            System.out.println("Standardised Decision Matrix");
            System.out.println("-----------------------------");
            for(i=0;i<6;i++){
                for(j=0;j<10;j++)
                    System.out.print(standardised_decision_matrix[service_no][i][j] + "\t");
                System.out.println();
            }


            //Constructing a weighted Standardised decision matrix
            for(i=0;i<6;i++){
                for(j=0;j<10;j++){
                    double temp;
                    temp=standardised_decision_matrix[service_no][i][j]*user_val[i];
                    temp=temp*100.0;
                    temp=Math.round(temp);
                    temp=temp/100.0;
                    weighted_std_matrix[service_no][i][j]=temp;
                }
            }

            //Printing the weighted Standardised decision matrix
            System.out.println("------------------------------");
            System.out.println("\nWeighted std Decision Matrix");
            System.out.println("------------------------------");
            for(i=0;i<6;i++){
                for(j=0;j<10;j++)
                    System.out.print(weighted_std_matrix[service_no][i][j] + "\t");
                System.out.println();
            }

            //Determining the Ideal and Negative Ideal Solutions
            double max_val=0,min_val=10;
            for(i=0;i<6;i++){
                max_val=0;
                min_val=10;
                for(j=0;j<10;j++){

                    if(weighted_std_matrix[service_no][i][j]>max_val)
                        max_val=weighted_std_matrix[service_no][i][j];

                    if(weighted_std_matrix[service_no][i][j]<min_val)
                        min_val=weighted_std_matrix[service_no][i][j];
                }

                ideal_soln[service_no][i]=max_val;
                n_ideal_soln[service_no][i]=min_val;
            }

            //Printing the Ideal Solution
            System.out.print("\n--------------");
            System.out.print("\nIdeal Solution");
            System.out.print("\n--------------\n");
            for(i=0;i<6;i++)
                System.out.println(ideal_soln[service_no][i]);

            //Printing the Negavtive Ideal Solution
            System.out.print("\n-----------------------");
            System.out.print("\nNegative Ideal Solution");
            System.out.print("\n-----------------------\n");
            for(i=0;i<6;i++)
                System.out.println(n_ideal_soln[service_no][i]);


            //Idealized Solution Set for Ideal Matrix
            for(i=0;i<6;i++)
            {
                for(j=0;j<10;j++)
                {
                    double temp;
                    temp=weighted_std_matrix[service_no][i][j]-ideal_soln[service_no][i];
                    temp=temp*temp;
                    temp=temp*100.0;
                    temp=Math.round(temp);
                    temp=temp/100.0;
                    ideal_soln_matrix[service_no][i][j]=temp;
                }
            }

            //Printing the Idealized Solution Matrix
            System.out.print("\n---------------------");
            System.out.print("\nThe idealized matrix:");
            System.out.print("\n---------------------\n");
            for(i=0;i<6;i++)
            {
                for(j=0;j<10;j++)
                {
                    System.out.print(ideal_soln_matrix[service_no][i][j] + "\t");
                }
                System.out.println();
            }

            //Idealized Solution Set for Negative Ideal Matrix
            for(i=0;i<6;i++)
            {
                for(j=0;j<10;j++)
                {
                    double temp;
                    temp=weighted_std_matrix[service_no][i][j]-n_ideal_soln[service_no][i];
                    temp=temp*temp;
                    temp=temp*100.0;
                    temp=Math.round(temp);
                    temp=temp/100.0;
                    n_ideal_soln_matrix[service_no][i][j]=temp;
                }
            }

            //Printing the Non-Idealized Matrix
            System.out.print("\n------------------------------");
            System.out.print("\nThe negative idealized matrix:");
            System.out.print("\n------------------------------\n");
            for(i=0;i<6;i++)
            {
                for(j=0;j<10;j++)
                {
                    System.out.print(n_ideal_soln_matrix[service_no][i][j] + "\t");
                }
                System.out.println();
            }

            //Calculatin the values of S*
            for(i=0;i<10;i++)
            {
                sum=0;
                for(j=0;j<6;j++)
                {
                    sum=sum+ideal_soln_matrix[service_no][j][i];
                }
                double temp;
                sum=Math.sqrt(sum);
                temp=sum;
                temp=temp*100.0;
                temp=Math.round(temp);
                temp=temp/100.0;
                s_one_star[service_no][i]=temp;
            }

            //Printing the Calculated S*
            System.out.print("\n----------");
            System.out.print("\nS_one_star");
            System.out.print("\n----------\n");
            for(i=0;i<10;i++)
                System.out.print(s_one_star[service_no][i] + "\t");
            System.out.println();

            //Calculating the values of S'
            for(i=0;i<10;i++)
            {
                sum=0;
                for(j=0;j<6;j++)
                {
                    sum=sum+n_ideal_soln_matrix[service_no][j][i];
                }
                double temp;
                sum=Math.sqrt(sum);
                temp=sum;
                temp=temp*100.0;
                temp=Math.round(temp);
                temp=temp/100.0;
                s_one_dash[service_no][i]=temp;
            }

            //Printing the Calculated S*
            System.out.print("\nS_one_dash");
            System.out.print("\n----------\n");
            for(i=0;i<10;i++)
                System.out.print(s_one_dash[service_no][i] + "\t");
            System.out.println();

            //Adding the values of S* and S'
            for(i=0;i<10;i++)
            {
                double temp;
                temp=s_one_star[service_no][i]+s_one_dash[service_no][i];
                temp=temp*100.0;
                temp=Math.round(temp);
                temp=temp/100.0;
                s_one_star_plus_dash[service_no][i]=temp;
            }

            //Printing the Addition Results
            System.out.print("\n------------");
            System.out.print("\nUpon adding:");
            System.out.print("\n------------\n");
            for(i=0;i<10;i++)
                System.out.print(s_one_star_plus_dash[service_no][i] + "\t");
            System.out.println();

            //Constructing the Resultant Solution
            //Formula : S'/(S*+S')
            for(i=0;i<10;i++)
            {
                double temp;
                temp=s_one_dash[service_no][i]/s_one_star_plus_dash[service_no][i];
                temp=temp*100.0;
                temp=Math.round(temp);
                temp=temp/100.0;
                resultant_solution[service_no][i]=temp;
            }

            //Printing the Resultant Solution
            System.out.println("\n----------------------------------------------------------------------------------");
            for(i=0;i<10;i++)
                System.out.print("Ser. " + (i+1) +"\t");
            System.out.println("\n----------------------------------------------------------------------------------");

            for(i=0;i<10;i++)
            {
                System.out.print(resultant_solution[service_no][i] + "\t");
                service[service_no][i].topsis_tag=resultant_solution[service_no][i];
            }

            //Printing the results of TOPSIS implementation
            System.out.print("\n---------------");
            System.out.print("\nTOPSIS Results:");
            System.out.print("\n---------------");
            System.out.print("\nService\tAvail\tCost\tFreq.\tReput.\tResp.\tSuccess\tTOPSIS\n");
            for(i=0;i<serviceCount;i++)
                service[service_no][i].printServiceQosWithTopsisTag();


            //Sorting the Topsis Results - Based on the Low,Medium & High categories identified during the Fuzzy approach
            for(i=0;i<high_count[service_no];i++)
            {
                for(j=i;j<high_count[service_no];j++)
                {
                    if(service[service_no][i].topsis_tag<service[service_no][j].topsis_tag)
                    {
                        serviceStore temp=service[service_no][i];
                        service[service_no][i]=service[service_no][j];
                        service[service_no][j]=temp;
                    }
                }
            }

            for(j=high_count[service_no];j<high_count[service_no]+medium_count[service_no];j++)
            {
                for(k=j;k<high_count[service_no]+medium_count[service_no];k++)
                {
                    if(service[service_no][j].topsis_tag<service[service_no][k].topsis_tag)
                    {
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][j];
                        service[service_no][j]=service[service_no][k];
                        service[service_no][k]=temp;
                    }
                }
            }

            for(k=high_count[service_no]+medium_count[service_no];k<high_count[service_no]+medium_count[service_no]+low_count[service_no];k++)
            {
                for(i=k;i<high_count[service_no]+medium_count[service_no]+low_count[service_no];i++)
                {
                    if(service[service_no][k].topsis_tag<service[service_no][i].topsis_tag)
                    {
                        serviceStore temp=new serviceStore();
                        temp=service[service_no][k];
                        service[service_no][k]=service[service_no][i];
                        service[service_no][i]=temp;
                    }
                }
            }

            System.out.print("\n-------------------------------------------------");
            System.out.print("\nOverall Results after Fuzzy Topsis implementation:");
            System.out.print("\n-------------------------------------------------");
            System.out.print("\nService\tTitle\tAvail\tCost\tFreq.\tReput.\tResp.\tSuccess\tTOPSIS\n");
            for(i=0;i<serviceCount;i++)
                service[service_no][i].printServiceQosWithTopsisTag();

            service_no++;

        }while(service_no < numOfServices);

        System.out.println("#######################################After results##################################");


        for(j=0;j<10;j++) {
            composition_string[j]="";
            tempComposition = new ArrayList<serviceStore>();
            for (i = 0; i < numOfServices; i++) {
                composition_string[j]+=String.format(" %35s%30s\n", fetchServiceName(services.get(i)) , service[i][j].getTitle());
                tempComposition.add(service[i][j]);
            }
            compositionMapping.put(j,tempComposition);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CompositionListAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareCompositionData();
    }

    public void prepareCompositionData(){
        CompositionList compositionListItem;
        for(i=0;i<10;i++){
            compositionListItem = new CompositionList("\nComposition " + String.valueOf(i+1), composition_string[i]);
            movieList.add(compositionListItem);
        }
    }

    public String fetchServiceName(String val){
        switch(val){
            case "0": return String.format("%20s","Cab");
            case "1" : return String.format("%20s","Clothing");
            case "2" : return String.format("%20s","Jewellery");
            case "3" : return String.format("%20s","Lodge");
            case "4" : return String.format("%20s","Restaurant");
            case "5" : return String.format("%20s","Shopping");
            case "6" : return String.format("%20s","Sites");
            case "7" : return String.format("%20s","Travel");
            default: return "";
        }
    }

    public void getSelectedComposition(){
        System.out.println("--------------Selected Composition-------------------------------------" + input_composition);
        for(serviceStore a: compositionMapping.get(input_composition_val-1)){
            selectedServicesForNextPage.add(a);
        }

    }

    public boolean validate() {
        boolean valid = true;

        if (input_composition.isEmpty()) {
            input.setError("Cannot be empty!");
            valid = false;
        } else {
            input_composition_val = Integer.parseInt(input_composition);
            if (input_composition_val <= 0 || input_composition_val > 10) {
                input.setError("Enter value between 1 and 10");
                valid = false;
            } else {
                valid = true;
            }
        }
        return valid;
    }
}
