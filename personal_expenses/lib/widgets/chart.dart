import 'package:flutter/material.dart';

class Chart extends StatelessWidget {
  const Chart({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5,
      child: Row(
        children: [
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Monday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Tuesday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Wednesday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Thursday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Friday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Saturday')
            ],
          ),
          Column(
            children: [
              Text('\$'),
              Container(
                color: Colors.red,
                height: 60,
                width: 5,
              ),
              Text('Sunday')
            ],
          ),
        ],
      ),
    );
  }
}
