import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CFormDetailsComponent } from './c-form-details.component';
import { CFormDetailsDetailComponent } from './c-form-details-detail.component';
import { CFormDetailsPopupComponent } from './c-form-details-dialog.component';
import { CFormDetailsDeletePopupComponent } from './c-form-details-delete-dialog.component';

@Injectable()
export class CFormDetailsResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const cFormDetailsRoute: Routes = [
    {
        path: 'c-form-details',
        component: CFormDetailsComponent,
        resolve: {
            'pagingParams': CFormDetailsResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'samplePmsApp.cFormDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'c-form-details/:id',
        component: CFormDetailsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'samplePmsApp.cFormDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cFormDetailsPopupRoute: Routes = [
    {
        path: 'c-form-details-new',
        component: CFormDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'samplePmsApp.cFormDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'c-form-details/:id/edit',
        component: CFormDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'samplePmsApp.cFormDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'c-form-details/:id/delete',
        component: CFormDetailsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'samplePmsApp.cFormDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
