import 'dart:io';

import 'package:flutter/material.dart';
import 'package:personal_expenses/models/transaction_model.dart';
import 'package:intl/intl.dart';

class TransactionList extends StatelessWidget {
  final List<Transaction_model> transaction;
  final Function deletetx;
  const TransactionList(this.transaction, this.deletetx);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 650,
      child: transaction.isEmpty
          ? Column(
              children: [
                Text(
                  'No List Created',
                  style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                ),
                Image.asset(
                  'images/image.png',
                  fit: BoxFit.cover,
                )
              ],
            )
          : ListView.builder(
              itemBuilder: (ctx, index) {
                /*return Card(
                  child: Row(
                    children: <Widget>[
                      Container(
                          decoration: BoxDecoration(
                              color: Colors.yellow,
                              border:
                                  Border.all(width: 2, color: Colors.green)),
                          margin: const EdgeInsets.all(10),
                          padding: EdgeInsets.all(10),
                          child: Text(
                            '\$: ${transaction[index].amount.toStringAsFixed(2)} ',
                            style: const TextStyle(
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          )),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            transaction[index].name,
                            style: const TextStyle(
                                fontSize: 20, fontWeight: FontWeight.bold),
                          ),
                          Text(
                            transaction[index].title,
                            style: TextStyle(
                                fontWeight: FontWeight.normal, fontSize: 20),
                          ),
                          Text(DateFormat.yMMMd()
                              .format(transaction[index].date)),
                        ],
                      ),
                      SizedBox(
                        width: 200,
                      ),
                      IconButton(
                        onPressed: () {
                          deletetx(transaction[index].id);
                        },
                        icon: Icon(Icons.delete),
                      ),
                    ],
                  ),
                );*/
                return ListTile(
                  leading: CircleAvatar(
                    radius: 30,
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text('\$: ${transaction[index].amount}'),
                    ),
                  ),
                  title: Text(transaction[index].title),
                  subtitle:
                      Text(DateFormat.yMMMd().format(transaction[index].date)),
                  trailing: IconButton(
                    onPressed: () {
                      deletetx(transaction[index].name);
                    },
                    icon: Icon(Icons.delete),
                  ),
                );
              },
              itemCount: transaction.length,
              //children: transaction.map((tx) {}).toList(),
            ),
    );
  }
}
