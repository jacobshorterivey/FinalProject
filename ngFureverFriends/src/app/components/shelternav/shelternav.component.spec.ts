import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelternavComponent } from './shelternav.component';

describe('ShelternavComponent', () => {
  let component: ShelternavComponent;
  let fixture: ComponentFixture<ShelternavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShelternavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShelternavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
