import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SiteResourcesComponent } from './site-resources.component';

describe('SiteResourcesComponent', () => {
  let component: SiteResourcesComponent;
  let fixture: ComponentFixture<SiteResourcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteResourcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
