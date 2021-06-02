import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NekretninaDialogComponent } from './nekretnina-dialog.component';

describe('NekretninaDialogComponent', () => {
  let component: NekretninaDialogComponent;
  let fixture: ComponentFixture<NekretninaDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NekretninaDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NekretninaDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
