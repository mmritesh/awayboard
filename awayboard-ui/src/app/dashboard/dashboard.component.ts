import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private router: Router,private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.employees = this.activatedRoute.snapshot.data['employees'].data;
    this.teams = this.activatedRoute.snapshot.data['teams'].data;
    console.log("this.teams: ", this.activatedRoute.snapshot.data['teams']);
  }

  
  public teams = [
    {
      "id": 1,
      "name": "Team 1",
      "imageUrl": "assets/images/sandeep.jpg",
      "employees": [
        {
          "id": 1,
          "name": "Ritesh",
          "imageUrl": "assets/images/sandeep.jpg",
          "currentStatus": "AWAY"
        }
      ]
    },
    {
      "id": 2,
      "name": "Team 2",
      "imageUrl": "assets/images/sandeep.jpg",
      "employees": [
        {
          "id": 1,
          "name": "Ritesh",
          "imageUrl": "assets/images/sandeep.jpg",
          "currentStatus": "AWAY"
        }
      ]
    },
    {
      "id": 3,
      "name": "Team 3",
      "imageUrl": "assets/images/sandeep.jpg",
      "employees": [
        {
          "id": 1,
          "name": "Ritesh",
          "imageUrl": "assets/images/sandeep.jpg",
          "currentStatus": "AWAY"
        }
      ]
    }
  ];

  public employees = [
    {
      "id": 1,
      "name": "Ritesh",
      "imageUrl": "assets/images/sandeep.jpg",
      "currentStatus": "AWAY"
    },
    {
      "id": 2,
      "name": "Abhilash",
      "imageUrl": "assets/images/sandeep.jpg",
      "currentStatus": "AWAY"
    }
  ];


}
