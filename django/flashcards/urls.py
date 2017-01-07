"""flashcards URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.conf import settings
from django.contrib import admin
from rest_framework import routers
from flashcards_app import views

# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet, "User")
router.register(r'groups', views.GroupViewSet, "Group")
router.register(r'flashcard-list', views.FlashcardListViewSet, "FlashcardList")
router.register(r'flashcard', views.FlashcardViewSet, "Flashcard")

# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.
urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),

    # REST API and REST API authentication.
    url(r'^api/', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
]

# Serve up frontend static files, but only in DEBUG during local development.
#if settings.DEBUG == True:
    #from django.contrib.staticfiles.urls import staticfiles_urlpatterns
    #urlpatterns += staticfiles_urlpatterns()
    #urlpatterns += patterns('',
    #    (r'^(?P<path>.*)$', 'django.views.static.serve',
    #    {'document_root':settings.STATIC_ROOT}),)

    #from django.conf.urls.static import static
    #urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
    #urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)

    #from django.views.static import serve
    #urlpatterns += [
    #    url(r'^static/(?P<path>.*)$', serve, {
    #        'document_root': settings.STATIC_ROOT,
    #    }),
    #    ]
