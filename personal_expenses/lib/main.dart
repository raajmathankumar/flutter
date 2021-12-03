import 'package:flutter/material.dart';
import 'package:personal_expenses/widgets/chart.dart';
import 'package:personal_expenses/widgets/new_transation.dart';
import 'package:personal_expenses/widgets/transation_list.dart';
import 'package:personal_expenses/widgets/user_transaction.dart';
import 'models/transaction_model.dart';
import 'package:intl/intl.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.red,
        fontFamily: 'Schyler',
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  //late String titleInput;
  //late String amountInput;
  final List<Transaction_model> _transactionList = [];

  void _addNewTranscation(
      String txTitle, double txamount, String txName, DateTime chosenDate) {
    final newtx = Transaction_model(
        chosenDate.toString(), txTitle, txamount, chosenDate, txName);
    setState(() {
      _transactionList.add(newtx);
    });
  }

  void deletetx(String id) {
    setState(() {
      _transactionList.removeWhere((tx) => tx.name == id);
    });
  }

  void _startAddNewTransaction(BuildContext Ctx) {
    showModalBottomSheet(
        context: Ctx,
        builder: (_) {
          return GestureDetector(
            child: NewTransaction(_addNewTranscation),
            onTap: () {},
            behavior: HitTestBehavior.opaque,
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Personal Expense'),
        actions: [
          IconButton(
              onPressed: () {
                _startAddNewTransaction(context);
              },
              icon: Icon(Icons.add))
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            //Chart(),
            //User_transaction(),
            TransactionList(_transactionList, deletetx)
          ],
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      floatingActionButton: FloatingActionButton(
        child: Icon(
          Icons.add,
        ),
        onPressed: () {
          _startAddNewTransaction(context);
        },
      ),
    );
  }
}
