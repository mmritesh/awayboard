import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwayboardComponent } from './awayboard.component';

describe('AwayboardComponent', () => {
  let component: AwayboardComponent;
  let fixture: ComponentFixture<AwayboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwayboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwayboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
