
from rest_framework import permissions

class IsCreatorOrReadOnly(permissions.BasePermission):
    """
    Custom permission to only allow creators/owners of an object to edit it.
    All others only have read access.
    """

    def has_object_permission(self, request, view, obj):
        # Read permissions are allowed to any request,
        # so we'll always allow GET, HEAD or OPTIONS requests.
        if request.method in permissions.SAFE_METHODS:
            return True

        # Write permissions are only allowed to the creator/owner of
        # the object.
        return obj.created_by_user == request.user

class IsCreator(permissions.BasePermission):
    """
    Custom permission to only allow creators/owners of an object to view or edit it.
    """

    def has_object_permission(self, request, view, obj):
        # Read and write permissions are only allowed to the creator/owner of
        # the object.
        return obj.created_by_user == request.user
