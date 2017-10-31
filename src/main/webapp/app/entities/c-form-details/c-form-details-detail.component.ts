import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CFormDetails } from './c-form-details.model';
import { CFormDetailsService } from './c-form-details.service';

@Component({
    selector: 'jhi-c-form-details-detail',
    templateUrl: './c-form-details-detail.component.html'
})
export class CFormDetailsDetailComponent implements OnInit, OnDestroy {

    cFormDetails: CFormDetails;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cFormDetailsService: CFormDetailsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCFormDetails();
    }

    load(id) {
        this.cFormDetailsService.find(id).subscribe((cFormDetails) => {
            this.cFormDetails = cFormDetails;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCFormDetails() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cFormDetailsListModification',
            (response) => this.load(this.cFormDetails.id)
        );
    }
}
