import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen extends JFrame {

    JTable newTable;

    DefaultTableModel tableModel;
    JTextField FName;
    JTextField LName;
    JTextField MoNum;
    JTextField City;
    JTextField StartC;
    JTextField EndC;

    JTextField NoTv;

    JLabel InstallFee;

    JLabel TotalFee;

    JLabel PackageFee;

    JCheckBox checkS;
    JCheckBox checkM;
    JCheckBox checkC;


    JLabel StartCycle;
    JLabel todayDate;
    JLabel EndCycle;
    JLabel NumberTV;

    SimpleDateFormat df;

    JLabel FirstName;
    JLabel LastName;
    JLabel MobileNumber;
    JLabel Cities;

    JTextArea ChannelC;
    JTextArea ChannelM;
    JTextArea ChannelS;

    int PackageSelectedPrice = 0;
    int TotalPrice;

    Subscription subscription;

    ArrayList<Subscription> listToSave = new ArrayList<>();

    File file;

    public MainScreen() {
        // Subscriber Details

        JPanel subscriberPanel = new JPanel();
        Border blackLine = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(blackLine);
        subscriberPanel.setBounds(15, 15, 300, 200);
        subscriberPanel.setLayout(new GridLayout(4, 2));

        FirstName = new JLabel("First Name");
        LastName = new JLabel("Last Name");
        MobileNumber = new JLabel("Mobile number");
        Cities = new JLabel("City");

        FName = new JTextField();
        LName = new JTextField();
        MoNum = new JTextField();
        City = new JTextField();

        subscriberPanel.add(FirstName);
        subscriberPanel.add(FName);
        subscriberPanel.add(LastName);
        subscriberPanel.add(LName);
        subscriberPanel.add(MobileNumber);
        subscriberPanel.add(MoNum);
        subscriberPanel.add(Cities);
        subscriberPanel.add(City);

        //Cycle Details

        JPanel cycleDetails = new JPanel();
        Border cycleLine = BorderFactory.createTitledBorder("Cycle Details");
        cycleDetails.setBorder(cycleLine);
        cycleDetails.setBounds(15, 230, 300, 500);
        cycleDetails.setLayout(new GridLayout(14, 1));

        StartC = new JTextField();
        EndC = new JTextField();
        NoTv = new JTextField();

        StartCycle = new JLabel("Start Date Cycle (DD/MM/YYYY)");
        EndCycle = new JLabel("End Date Cycle (DD/MM/YYYY)");
        NumberTV = new JLabel("Number of TV");
        todayDate = new JLabel();
        df = new SimpleDateFormat();

        Date currentDate = new Date();
        todayDate.setText("Today: " + df.format(currentDate));

        cycleDetails.add(todayDate);
        cycleDetails.add(StartCycle);
        cycleDetails.add(StartC);
        cycleDetails.add(EndCycle);
        cycleDetails.add(EndC);
        cycleDetails.add(NumberTV);
        cycleDetails.add(NoTv);

        StartC.setOpaque(false);
        EndC.setOpaque(false);
        NoTv.setOpaque(false);

        //Package Details
        JPanel packageDetails = new JPanel();
        Border packageBorder = BorderFactory.createTitledBorder("Package Details");
        packageDetails.setBorder(packageBorder);
        packageDetails.setBounds(330, 15, 300, 200);
        packageDetails.setLayout(new GridLayout(5, 1));

        JLabel pack = new JLabel("Please Select your Package");
        checkS = new JCheckBox("Sports Package");
        checkM = new JCheckBox("Movie Package");
        checkC = new JCheckBox("Cartoon Package");
        JButton button1 = new JButton("Subscribe");
        checkS.setOpaque(false);
        checkM.setOpaque(false);
        checkC.setOpaque(false);

        packageDetails.add(pack);
        packageDetails.add(checkS);
        packageDetails.add(checkM);
        packageDetails.add(checkC);
        packageDetails.add(button1);


        // Check for the channels
        checkM.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkM.isSelected()) {
                    DisplayMovieChannels();
                } else {
                    ChannelM.setText("");
                }
            }
        });
        checkS.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkS.isSelected()) {
                    DisplaySportChannels();
                } else {
                    ChannelS.setText("");
                }
            }
        });
        checkC.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (checkC.isSelected()) {
                    DisplayCartoonChannels();
                } else {
                    ChannelC.setText("");
                }
            }
        });
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getSubscriberData();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Package Details
        JPanel packageDesc = new JPanel();
        Border packageLine = BorderFactory.createTitledBorder("Available Channels");
        packageDesc.setBorder(packageLine);
        packageDesc.setBounds(330, 245, 300, 500);
        packageDesc.setLayout(new GridLayout(3, 1));

        ChannelS = new JTextArea(5, 1);
        ChannelS.setEditable(false);
        ChannelS.setOpaque(false);
        ChannelS.setLineWrap(true);

        ChannelM = new JTextArea(5, 1);
        ChannelM.setEditable(false);
        ChannelM.setOpaque(false);
        ChannelM.setLineWrap(true);

        ChannelC = new JTextArea(5, 1);
        ChannelC.setEditable(false);
        ChannelC.setOpaque(false);
        ChannelC.setLineWrap(true);

        packageDesc.add(ChannelM);
        packageDesc.add(ChannelS);
        packageDesc.add(ChannelC);

        //Package Fee
        JPanel packageFee = new JPanel();
        Border FeeLine = BorderFactory.createTitledBorder("Fee & Check");
        packageFee.setBorder(FeeLine);
        packageFee.setBounds(645, 15, 200, 200);
        packageFee.setLayout(new GridLayout(3, 1));

        InstallFee = new JLabel("Installation Fee:");
        PackageFee = new JLabel("Packages Fee:");
        TotalFee = new JLabel("Total Amount to Pay:");

        packageFee.add(InstallFee);
        packageFee.add(PackageFee);
        packageFee.add(TotalFee);

        //Total costumers
        JPanel customers = new JPanel();
        Border customerLine = BorderFactory.createTitledBorder("Our Customers");
        customers.setBorder(customerLine);
        customers.setBounds(645, 245, 515, 500);
        customers.setLayout(new GridLayout(3, 1));

        tableModel = new DefaultTableModel();
        newTable = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        JScrollPane scrollPane = new JScrollPane(newTable);
        customers.add(scrollPane);

        //Action bar
        JPanel action = new JPanel();
        Border actionLine = BorderFactory.createTitledBorder("Action Tab");
        action.setBorder(actionLine);
        action.setBounds(860, 15, 300, 200);
        action.setLayout(new GridLayout(3, 1));

        JButton buttonNS = new JButton("New Subscription");
        JButton buttonSS = new JButton("Save Subscription");
        JButton buttonLS = new JButton("Load Subscription");

        action.add(buttonLS);
        action.add(buttonNS);
        action.add(buttonSS);

        setLayout(null);
        add(subscriberPanel);
        add(cycleDetails);
        add(packageDetails);
        add(packageDesc);
        add(packageFee);
        add(customers);
        add(action);

        buttonNS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        buttonLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Subscription> k = LoadSubscription();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonSS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscription();
            }
        });
    }

    ;


    public void getSubscriberData() throws ParseException {
        Date currentDate = new Date();

        Subscriber subscriber = new Subscriber(
                FName.getText(),
                LName.getText(),
                Integer.parseInt(MoNum.getText()),
                City.getText()
        );

        //Subscription Cycle
        String formatPattern = "dd/MM/yyyy";

        // Create a SimpleDateFormat instance
        DateFormat df = new SimpleDateFormat(formatPattern);

        Date startCycle = df.parse(StartC.getText());
        Date endCycle = df.parse(EndC.getText());
        SubscriptionCycle subscriptionCycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        //Subscription
        subscription = new Subscription(
                Integer.parseInt(NoTv.getText()),
                subscriber,
                subscriptionCycle,
                df.format(currentDate)
        );

        InstallFee.setText("Total installation Fee: " + subscription.getTotalFee() + " $");
        showPrice();
    }

    private void showPrice() {
        if (checkM.isSelected()) {
            PackageSelectedPrice += DisplayMovieChannels();
        } else if (checkS.isSelected()) {
            PackageSelectedPrice += DisplaySportChannels();
        } else if (checkC.isSelected()) {
            PackageSelectedPrice += DisplayCartoonChannels();
        }
        PackageFee.setText("Total package Fee: " + PackageSelectedPrice + " $");
        TotalPrice = subscription.getTotalFee() + PackageSelectedPrice;
        TotalFee.setText("Total amount to Pay: " + TotalPrice + " $");
    }

    private int DisplayMovieChannels() {
        MovieChannel m1 = new MovieChannel("Hungama", "Romantic", "English");
        MovieChannel m2 = new MovieChannel("StarGold", "Horror", "Hindi");
        MovieChannel m3 = new MovieChannel("StarPlus", "Thriller", "Hindi");
        MovieChannel m4 = new MovieChannel("StarMovies", "Comedy", "Spanish");
        MovieChannel m5 = new MovieChannel("A1", "Fantasy", "Nepali");

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);

        String movieChannel = "";
        int packagePrice = 0;
        ChannelM.setText("");
        for (int i = 0; i < movieList.size(); i++) {
            movieChannel += "     " + movieList.get(i).getChannelName()
                    + "     " + movieList.get(i).getCategory()
                    + "     " + movieList.get(i).getLanguage()
                    + "\n";
            packagePrice += movieList.get(i).getPrice();
        }
        ChannelM.setText(movieChannel);
        return packagePrice;

    };

    private int DisplaySportChannels() {
        SportChannel s1 = new SportChannel("ESPN", "Basketball", "English");
        SportChannel s2 = new SportChannel("Fox Sports", "Soccer", "Spanish");
        SportChannel s3 = new SportChannel("Star Sports", "Cricket", "Hindi");
        SportChannel s4 = new SportChannel("Sky Sports", "Tennis", "English");
        SportChannel s5 = new SportChannel("Sony Six", "Wrestling", "Hindi");

        ArrayList<SportChannel> sport = new ArrayList<>();
        sport.add(s1);
        sport.add(s2);
        sport.add(s3);
        sport.add(s4);
        sport.add(s5);

        String sportChannel = "";
        int packagePrice = 0;
        ChannelS.setText("");
        for (int i = 0; i < sport.size(); i++) {
            sportChannel += "   " + sport.get(i).getChannelName()
                    + "   " + sport.get(i).getCategory()
                    + "   " + sport.get(i).getLanguage()
                    + "\n";
            packagePrice += sport.get(i).getPrice();


        }
        ChannelS.setText(sportChannel);
        return packagePrice;

    };

    private int DisplayCartoonChannels() {
        CartoonChannel c1 = new CartoonChannel("Cartoon Network", "Children", "English");
        CartoonChannel c2 = new CartoonChannel("Disney Channel", "Adult", "Spanish");
        CartoonChannel c3 = new CartoonChannel("Nickelodeon", "Children", "Hindi");
        CartoonChannel c4 = new CartoonChannel("Boomerang", "Children", "English");
        CartoonChannel c5 = new CartoonChannel("Pogo", "Old", "Hindi");

        ArrayList<CartoonChannel> cartoon = new ArrayList<>();
        cartoon.add(c1);
        cartoon.add(c2);
        cartoon.add(c3);
        cartoon.add(c4);
        cartoon.add(c5);

        String cartoonChannel = "";
        int packagePrice = 0;
        ChannelC.setText("");
        for (int i = 0; i < cartoon.size(); i++) {
            cartoonChannel += "   " + cartoon.get(i).getChannelName()
                    + "   " + cartoon.get(i).getCategory()
                    + "   " + cartoon.get(i).getLanguage()
                    + "\n";
            packagePrice += cartoon.get(i).getPrice();


        }
        ChannelC.setText(cartoonChannel);
        return packagePrice;
    }


    private void DisplayDataInTable(Subscription sub) {
        tableModel.addRow(new Object[]{
                sub.getSubscriber().getfName(),
                sub.getSubscriber().getlName(),
                sub.getSubscriber().getPhoneNumber(),
                sub.getSubscriptionCycle().getStartDate(),
                sub.getSubscriptionCycle().getEndDate(),
                sub.getTotalFee()
        });
    }

    private void NewSubscription() {
        //All are empty
        FName.setText("");
        LName.setText("");
        MoNum.setText("");
        City.setText("");
        StartC.setText("");
        EndC.setText("");

        InstallFee.setText("Total installation Fee: ");
        PackageFee.setText("Total Package Fee: ");
        TotalFee.setText("Total Amount to Pay: ");

        checkS.setSelected(false);
        checkM.setSelected(false);
        checkC.setSelected(false);

    }

    private void SaveSubscription() {
        listToSave.add(subscription);
        file = new File("D:\\myFile.dat");

        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Subscription> LoadSubscription() throws FileNotFoundException {
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("D:\\myFile.dat");

        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream iis = new ObjectInputStream(is);

            s = (ArrayList) iis.readObject();
            iis.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Subscription sub : s) {
            DisplayDataInTable(sub);
        }
        return s;
    }

    ;

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setBounds(100, 100, 1200, 800);
        mainScreen.setVisible(true);
    }
}
