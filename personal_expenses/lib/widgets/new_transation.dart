import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class NewTransaction extends StatefulWidget {
  final Function addTx;

  NewTransaction(this.addTx);

  @override
  State<NewTransaction> createState() => _NewTransactionState();
}

class _NewTransactionState extends State<NewTransaction> {
  final _titleController = TextEditingController();
  DateTime? _selectedDate;
  final _amountController = TextEditingController();

  final nameController = TextEditingController();

  void _summitData() {
    final enterTitle = _titleController.text;
    final enterAmount = double.parse(_amountController.text);
    final enterName = nameController.text;

    if (enterName.isEmpty ||
        enterAmount <= 0 ||
        enterAmount < 100 ||
        enterTitle.isEmpty ||
        _selectedDate == null) {
      return;
    }

    widget.addTx(enterName, enterAmount, enterTitle, _selectedDate);

    Navigator.of(context).pop();
  }

  void _presentDatePicker() {
    showDatePicker(
            context: context,
            initialDate: DateTime.now(),
            firstDate: DateTime(2021),
            lastDate: DateTime.now())
        .then((pickedDate) {
      if (pickedDate == null) {
        return;
      }
      setState(() {
        _selectedDate = pickedDate;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(10),
      child: Card(
        elevation: 5,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            TextField(
              decoration: InputDecoration(labelText: 'Name'),
              controller: nameController,
              onSubmitted: (_) => _summitData(),
              autofocus: false,
            ),
            TextField(
              decoration: InputDecoration(labelText: 'Title'),
              controller: _titleController,
              onSubmitted: (_) => _summitData(),
              autofocus: false,
            ),
            TextField(
              decoration: InputDecoration(labelText: 'Amount'),
              controller: _amountController,
              keyboardType: TextInputType.number,
              onSubmitted: (_) => _summitData(),
              autofocus: false,
            ),
            Container(
              height: 70,
              child: Row(
                children: [
                  Text(
                    _selectedDate == null
                        ? 'No Date Choosen'
                        : 'Picked Date: ${DateFormat.yMd().format(_selectedDate!)}',
                  ),
                  ElevatedButton(
                      onPressed: _presentDatePicker, child: Text('Choose Date'))
                ],
              ),
            ),
            ElevatedButton(
                onPressed: () {
                  print(_titleController.text);
                  print(_amountController.text);
                  _summitData();
                },
                child: Text(
                  'Add Transaction',
                ))
          ],
        ),
      ),
    );
  }
}
